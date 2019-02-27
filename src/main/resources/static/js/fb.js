var loginPage = "http://localhost:8080/app/login";
var initPage = "http://localhost:8080/app/input";

window.fbAsyncInit = function() {
	
	FB.init({
	      appId      : '1960512807329068',
	      cookie     : true,  // enable cookies to allow the server to access the session
	      xfbml      : true,  // parse social plugins on this page
	      version    : 'v3.2' // The Graph API version to use for the call
	});

	FB.getLoginStatus(function(response) {
		  if (response.status != 'connected' && loginPage != window.location) {
			  window.location = loginPage;  
		  }
	});

	FB.Event.subscribe('auth.login', function(){
		if(loginPage != window.location) {
			return;
		}
		
		window.location = initPage;
    });

};
  
function logout() {
      FB.logout(function(response) {
	  window.location = loginPage;
      });
}

(function(d, s, id) {
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) return;
    js = d.createElement(s); js.id = id;
    js.src = "https://connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));  