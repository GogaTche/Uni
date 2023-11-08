<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Evas Forum</title>
    <link rel="icon" type="images/x-icon" href="images\EF.png"/>   
  </head>

  <body class="bg-black">
    <div class="container-fluid d-flex mx-0 my-1 pe-2 ps-2 pb-1 border-bottom"> 
        <div class="col d-flex"> 
            <a id="homeIcon" href="index.php"><img src="images\EF.png" height="55" width="85"></a>
       </div>
       <div class="col d-flex justify-content-end px-2 pt-1">
            <a href="blog.php"><img src="images\post.png" height="50" width="50"></a>
        	<a href="blog.php"class = "text-decoration-none text-white pt-2"><h3>Post<h3></a>
        </div>
    </div>

    <form {if $update} action="updateBlog_action.php?microposts_id={$post_id}&microposts_user_id={$user_id}" {else} action="newBlog_action.php" {/if} class = "container-md my-5 mb-2 px-0 justify-content-end align-items-center" method="post">
        <div class="input-group">
            <textarea class="form-control bg-black text-white" placeholder="What's on your mind?" name="content">{$content}</textarea>
            <button class = "btn btn-outline-light d-inline">Post</button>
        </div>
    </form>

  </body>

</html> 