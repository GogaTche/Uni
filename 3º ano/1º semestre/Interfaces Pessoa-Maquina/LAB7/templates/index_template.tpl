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
      
      <div class = "col d-flex justify-content-center align-items-center">
        <input style = "outline: none;"class ="rounded-4 bg-black w-75 h-75 border-0 text-white" type="search" name="search" placeholder="Search posts" ></input>
      </div>

      {if $isLoggedIn}
        <div class = "col d-flex justify-content-end align-items-center">
          <h4 class = "text-white pe-3 pt-1">Welcome back!</h4>
          <img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="45" width="45">
          <h4 class = "text-white px-2 pt-1">{$name}</h4>
          <a class="btn btn-outline-light d-inline mx-1" href="logout_action.php" role="button">Logout</a>
        </div>
      {else}
        <div class = "col d-flex justify-content-end align-items-center">
          <a class="btn btn-outline-light d-inline mx-1" href="login.php" role="button" >Login</a>
          <a class="btn btn-outline-light d-inline mx-1" href="register.php" role="button" >Register</a>
        </div>
      {/if}
    </div>

    {if $isLoggedIn}
      <div class = "container-md col d-flex justify-content-start align-items-center pt-5">
          <a href="blog.php"><img src="images\post.png" height="45" width="45"></a>
          <a href="blog.php"class = "text-decoration-none text-white pt-2"><h4>Post<h4></a>
      </div>
    {/if}

  {foreach item = posts from = $microposts}
    <div style="word-wrap: break-word" class = "container-xl my-5 border border-1 p-1 rounded-2"> 
      <div class = "col d-flex justify-content-start align-items-center p-2">
        <img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="60" width="60">
        <h1 class="ps-2 text-white">{$posts.name}</h1>
        <h5 class="ps-3 pt-2 text-white">created at {$posts.created_at}</h5><h6 class="ps-3 pt-2 text-white opacity-50">updated at {$posts.updated_at}</h6>
        {if $posts.user_id == $id}
          <div class="col d-flex container-sm justify-content-end align-items-right mb-2">
            <button class="btn btn-sm btn-outline-light d-inline mx-1 float-right" onclick="location.href='blog.php?post_id={$posts.id}'">Update</button>
          </div>
        {/if}
      </div>
      <div style="word-wrap: break-word" class = "justify-content-start align-items-center p-2">
        <h5 class ="text-white">{$posts.content}</h5>
      </div>
    </div>
  {/foreach}

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>

</html> 