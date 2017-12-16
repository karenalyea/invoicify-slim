$(function () {
	
	$('#create-rate-based-form').submit(function (e) {
		e.preventDefault();
		
		let rateBased = {
			rate: $('#rate').val(),
			quantity: $('#quantity').val(),
			client: { id: $('#rate-based-clientId').val() }
			description: $('#rate-based-description').val(),
		};
		
		let headers = {
				'X-CSRF-TOKEN' : $('#rate-based-csrf').val()
		};
		
		let settings = {
				url: 'api/rate-baseds',
				headers: headers,
				data: JSON.stringify(rateBased),
				contentType: 'application/json'
				
		};
		
		$.post(settings)
		.done(function (data) {
			$('#billing-table')
			.append(`
					<tr>
						<td>{data.createdBy.username}</td>
						<td>${data.description}</td>
						<td>${data.client.name}</td>
						<td>${data.amount}</td>
						<td>${data.rate}</td>
						<td>${data.quantity}</td>
						<td>${data.total }</td>
					</tr>`);
					
					$('#rate').val('');
					$('#quantity').val('');
					$('#rate-based-clientId').val('');
					$('#rate-based-desription').val('');
				
		});
		
	});
	
});