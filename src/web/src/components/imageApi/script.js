define([], function(){

	var elms = {
		searchUrl: "https://ajax.googleapis.com/ajax/services/search/images?userip=192.168.0.100&v=1.0&imgsz=icon&q="
	}

	var Image = function(){
		var getImgUrl = function(text, index, callback){
			console.log(elms.searchUrl + text);

			$.ajax({
				url: elms.searchUrl + text,
				dataType: 'jsonp',
				success: function(dataWeGotViaJsonp){
					var idx = index || 0;
					var imgUrl = dataWeGotViaJsonp.responseData.results[idx].url;
					if (callback != undefined){
						callback({name: text, imgUrl: imgUrl});
					}
				}	
			});
		}

		return {
            getImgUrl: getImgUrl
		}
	}

	return new Image();
})


