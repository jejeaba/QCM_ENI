function edit(e, dom){
	e.preventDefault();
	var id = $(dom).data("id");
	console.log(id);
	$.ajax({
	  url: "<%= request.getContextPath() %>/view"+title,
	  method: 'POST',
	  data: {id: id, action: "edit"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Modifier '+title,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function add(e, dom){
	e.preventDefault();
	var id = $(dom).data("id");
	console.log(id);
	$.ajax({
	  url: "<%= request.getContextPath() %>/view"+title,
	  method: 'POST',
	  data: {id: id,action: "add"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Ajouter '+title,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function remove(e, dom){
	e.preventDefault();
	var id = $(dom).data("id");
	console.log(id);
	$.ajax({
	  url: "<%= request.getContextPath() %>/view"+title,
	  method: 'POST',
	  data: {id: id, action: "delete"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			type:BootstrapDialog.TYPE_DANGER,
			title: 'Suppression '+title,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	    });   
	 
}