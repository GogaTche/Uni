<?php /* Smarty version Smarty-3.1.21-dev, created on 2023-10-30 21:43:14
         compiled from "templates/login_template.tpl" */ ?>
<?php /*%%SmartyHeaderCode:36159328465401fa21563d8-33864004%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '09e5371769d94a97e2db8dfd49472ff8bd1b4113' => 
    array (
      0 => 'templates/login_template.tpl',
      1 => 1698702244,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '36159328465401fa21563d8-33864004',
  'function' => 
  array (
  ),
  'version' => 'Smarty-3.1.21-dev',
  'unifunc' => 'content_65401fa2197656_22066454',
  'has_nocache_code' => false,
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_65401fa2197656_22066454')) {function content_65401fa2197656_22066454($_smarty_tpl) {?><!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Evas Forum</title>
  </head>
  <body class = "d-flex bg-black justify-content-center align-items-center vh-100">
    <div class = "d-gird container-sm rounded-2 border border-2 justify-content-center align-text-center w-auto">
        <div class = "mb-4 d-flex justify-content-center align-text-center">
            <h1 class="text-white">Login</h1>
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter email:</label>
            <input type="text" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label text-white">Enter password:</label>
            <input type="password" class="form-control bg-black text-white" id="formGroupExampleInput2">
        </div>
        <div class="mb-4 d-flex justify-content-center align-text-center">
            <button class = "btn btn-outline-light d-inline mt-4 mb-1">Login</button>
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
    </div>
  </body>
</html> <?php }} ?>
