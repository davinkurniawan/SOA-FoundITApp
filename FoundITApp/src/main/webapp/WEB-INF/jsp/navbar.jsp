
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/FoundITApp/home">FoundIT Co.</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/FoundITApp/logout">Sign out</a></li>
            <c:if test="${role == 'app-manager'}">
            <li><a href="/FoundITApp/newJobForm">Post a new Job</a></li>
            </c:if>
			<c:if test="${role == 'app-candidate'}">
            <li><a href="/FoundITApp/advancedSearch">Advanced Search</a></li>
       	</ul>
        <div id="navbar" class="navbar-collapse collapse">
		<form class="navbar-form navbar-right" action="searchJobs">
			<div class="form-group">
				<input type="text" placeholder="Search on Job Title..." name="positiontitle" class="form-control">
			</div>
			<button id="submitbtn" type="submit" class="btn btn-success">Search</button>
		</form>      
		  <script>
		    (function() {
		        var search = document.getElementById('search');
		        search.addEventListener('keypress', function(event) {
		            if (event.keyCode == 13) {
		                event.preventDefault();
		                document.getElementById('submitbtn').click();
		            }
		        });
		    }());
		  </script>
		  	</c:if>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>