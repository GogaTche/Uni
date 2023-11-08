<?php
/* Smarty version 3.1.33-dev-7, created on 2023-11-05 02:37:29
  from '/users/a76943/public_html/LAB5/templates/message_template.tpl' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.33-dev-7',
  'unifunc' => 'content_6546f1d962a853_56595765',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '9207f4280127a870fe4f6fc395a6b40c1b26cb68' => 
    array (
      0 => '/users/a76943/public_html/LAB5/templates/message_template.tpl',
      1 => 1699148283,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_6546f1d962a853_56595765 (Smarty_Internal_Template $_smarty_tpl) {
?><!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <title>Evas Forum</title>
    </head>
    <body class = "d-flex bg-black justify-content-center align-items-center vh-100">
        <div class = "d-gird container-sm rounded-2 border border-2 text-center w-25">
            <h1 class="text-white"><?php echo $_smarty_tpl->tpl_vars['message']->value;?>
</h1>
        </div>
    </body>

<meta http-equiv="refresh" content="3; url=index.php"/><?php }
}
