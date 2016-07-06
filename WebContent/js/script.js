function edit(e, dom){
	e.preventDefault();
	var id = $(dom).data("id");
	$.ajax({
	  url: "/"+window.location.pathname.split("/")[1]+"/admin/view"+title,
	  method: 'POST',
	  data: {id: id, action: "edit"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Modifier '+title,
			size: BootstrapDialog.SIZE_SMALL,
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
	$.ajax({
	  url: "/"+window.location.pathname.split("/")[1]+"/admin/view"+title,
	  method: 'POST',
	  data: {id: 0,action: "add"}
	}).done(function(view) {
		var dialog = new BootstrapDialog({
			title: 'Ajouter '+title,
			size: BootstrapDialog.SIZE_SMALL,
	        message: view,
	        nl2br: false
	    });
		dialog.realize();
		dialog.getModalFooter().hide();
		dialog.open();
	});
	 
}
function supp(e, dom){
	e.preventDefault();
	var id = $(dom).data("id");
	$.ajax({
	  url: "/"+window.location.pathname.split("/")[1]+"/admin/view"+title,
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