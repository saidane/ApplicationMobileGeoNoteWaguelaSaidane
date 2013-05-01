var NotesApp=(function(){
	var App={
		stores:{},
		views:{}
	}
	App.stores.notes=new Backbone.LocalStorage('notes');	

	
	var Note= Backbone.Model.extend({
	
		localStorage:App.stores.notes,     //pas internet
		// url: 'http://mosupper.com/notes'  // internet
		
		initialize:function() {
			if(!this.get('title')){
				this.set({title:"Note @ "+Date()});
			};
			
			if(!this.get('body')){
				this.set({body:"no cntent "})
			};
		}
	});
	
		
	window.Note= Note;
	return App;
})();