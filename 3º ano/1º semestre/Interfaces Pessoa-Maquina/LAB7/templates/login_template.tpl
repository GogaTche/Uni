<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Evas Forum</title>
  </head>
  <body class = "d-flex bg-black justify-content-center align-items-center vh-100">
    <form action = "login_action.php" method = "post" class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white">{$message}</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="text" name="email" value="{$email}" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" name="password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Login</button>
        </div>
        <div class="mb-2 d-flex justify-content-center align-text-center form-check">
            <input class="form-check-input" type="checkbox" value="1" id="autologin" name="autologin">
            <label class="text-white form-check-label" for="autologin">
                 Remember Me
            </label>
        </div>
        <div class="mb-2 d-flex justify-content-center align-text-center">
            <a href=#>Forgot password?</a>
        </div>
    </div>
        <div class=" d-flex justify-content-center align-text-center">
            <h6 class="text-white">Don't have an account? <br> 
                <div class="d-flex justify-content-center align-text-center">
                    <a href="register.php">Register here</a>
                </div>
            </h6>
        </div>
        <div class="mt-3 d-flex justify-content-center align-text-center">
            <h6 class="text-white">Go home <a href="index.php">here</a>
            </h6>
        </div>
    </form>
  </body>
</html> 