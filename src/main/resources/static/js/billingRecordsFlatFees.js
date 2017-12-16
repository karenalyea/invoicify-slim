$(function () {
	
	$('#create-flat-fee-form').submit(function (e) {
		e.preventDefault();
		
		let flatFee = {
			amount: $('#flat-fee-amount').val(),
			client: { id: $('#flat-fee-clientId').val() }
			description: $('#flat-fee-description').val(),
		};
	
		let headers = {
				'X-CSRF-TOKEN': $('#flat-fee-csrf').val()
		};
		
		let settings = {
				url: '/api/flatfees',
				headers: headers,
				data: JSON.stringify(flatFee),
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
						
			$('#flat-fee-amount').val('');
			$('#flat-fee-description').val('');
			$('#flat-fee-clientId').val('');
		});
	});
});