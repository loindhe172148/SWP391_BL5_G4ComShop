const search_input = document.getElementById('search-input');
search_input.addEventListener('keyup', () => {
    const input = search_input.value;
    $.ajax({
        url: "/SWP391_BL5_G4ComShop/admin/searchUserController",
        type: 'GET',
        dataType: 'json',
        data: {
            'searchkey': input
        },
        success: function (response) {
            let table= "";
            const listUser = response.users;

            listUser.forEach(u => {
                console.log(u);
                table += `<tr>
                                    <td>${u.ID}</td>
                                    <td>${u.email}</td>
                                    <td>${u.address}</td>
                                    <td>${u.gender}</td>
                                    <td>${u.phone}</td>
                                    <td>${u.dob}</td>
                                    <td>${u.status}</td>
                                    <td>
                                        <a href="viewUserDetail?userId=${u.ID}"
                                           class="btn btn-info btn-sm">View Details</a>
                                        <a href="viewUserDetail?userId=${u.ID}"
                                           class="btn btn-info btn-sm">Edit</a>
                                    </td>
                                </tr>`;
            });
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = table;
        }
    });
});