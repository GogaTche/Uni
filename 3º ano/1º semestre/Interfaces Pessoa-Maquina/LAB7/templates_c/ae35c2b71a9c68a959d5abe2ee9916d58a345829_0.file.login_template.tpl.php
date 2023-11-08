<?php
/* Smarty version 3.1.33-dev-7, created on 2023-11-05 02:37:27
  from '/users/a76943/public_html/LAB5/templates/login_template.tpl' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.33-dev-7',
  'unifunc' => 'content_6546f1d793b943_80387727',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'ae35c2b71a9c68a959d5abe2ee9916d58a345829' => 
    array (
      0 => '/users/a76943/public_html/LAB5/templates/login_template.tpl',
      1 => 1699148283,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_6546f1d793b943_80387727 (Smarty_Internal_Template $_smarty_tpl) {
?><!doctype html>
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
            <h1 class="text-white"><?php echo $_smarty_tpl->tpl_vars['message']->value;?>
</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="text" name="email" value="<?php echo $_smarty_tpl->tpl_vars['email']->value;?>
" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" name="password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Login</button>
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
</html> <?php }
}
