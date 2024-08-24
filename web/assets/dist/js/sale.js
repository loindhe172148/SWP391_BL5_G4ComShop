const search_input = document.getElementById('search-input');
search_input.addEventListener('keyup', () => {
    const input = search_input.value;
    $.ajax({
        url: "/SWP391_BL5_G4ComShop/sale/search",
        type: 'GET',
        dataType: 'json',
        data: {
            'searchkey': input
        },
        success: function (response) {
            let table= "";
            const listUser = response.orders;

            listUser.forEach(u => {
                console.log(u);
                table += `<tr>
                                    <td>${u.ID}</td>
                                    <td>${u.customername}</td>
                                    <td>${u.orderdate}</td>
                                    <td>${u.total}</td>
                                    <td>${u.address}</td>
                                    <td style="color: ${u.status === 'declined' ?'red':'green'};font-weight: 700;">${u.status === 'processing' ?
                                                        `<a href="#" class="btn btn-danger" onclick="changeStatus(${u.ID},'declined')">Decline</a>
                                                        <a href="#" class="btn btn-success" onclick="changeStatus(${u.ID},'delivering')">Approved</a>`: u.status}</td>
                                    <td><a href="orderdetail?id=${u.ID}"
                                          class="btn btn-info btn-sm">View Details</a></td>
                                </tr>`;
            });
            const tbody = document.querySelector('table tbody');
            tbody.innerHTML = table;
        }
    });
});


function changeStatus(id, status){
    $.ajax({
        url: "/SWP391_BL5_G4ComShop/sale/order",
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify({
           'id':id,
           'status':status
        }),
        success: function (response){
            if(response.code === '00'){
                location.reload();
            }else{
                window.alert(response.message);
            }
        }
    });
}