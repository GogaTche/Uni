<?php
/* Smarty version 3.1.33-dev-7, created on 2023-11-05 03:23:56
  from '/users/a76943/public_html/LAB5/templates/register_template.tpl' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.33-dev-7',
  'unifunc' => 'content_6546fcbc4c9a11_61798226',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '1639e771c63ed09179d1a398cafde484cc017d27' => 
    array (
      0 => '/users/a76943/public_html/LAB5/templates/register_template.tpl',
      1 => 1699148283,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_6546fcbc4c9a11_61798226 (Smarty_Internal_Template $_smarty_tpl) {
?><!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Evas Forum</title>
  </head>
  <body class = "d-flex bg-black justify-content-center align-items-center vh-100">
    <form action = "register_action.php" method = "post" class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white"><?php echo $_smarty_tpl->tpl_vars['message']->value;?>
</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter Username:</label>
            <input type="text" value="<?php echo $_smarty_tpl->tpl_vars['username']->value;?>
" name="username" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="email" value="<?php echo $_smarty_tpl->tpl_vars['email']->value;?>
" name="email" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" name="password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Confirm password:</label>
            <input type="password" name="confirm_password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button type="submit" class = "btn btn-outline-light d-inline mt-4 mb-1">Register</button>
        </div>
        <div class=" d-flex justify-content-center align-text-center">
            <h6 class="text-white">Already have an account? <br> 
                <div class="d-flex justify-content-center align-text-center">
                    <a href="login.php">Login here</a>
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
