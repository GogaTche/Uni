<?php /* Smarty version Smarty-3.1.21-dev, created on 2023-10-30 21:43:24
         compiled from "templates/index_template.tpl" */ ?>
<?php /*%%SmartyHeaderCode:794232713653fe90e8b16f4-63936036%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '1a3b8f63b5d45951ad3b4eea6e77a64e0708982a' => 
    array (
      0 => 'templates/index_template.tpl',
      1 => 1698702244,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '794232713653fe90e8b16f4-63936036',
  'function' => 
  array (
  ),
  'version' => 'Smarty-3.1.21-dev',
  'unifunc' => 'content_653fe90e8dd226_47285406',
  'variables' => 
  array (
    'microposts' => 0,
    'posts' => 0,
  ),
  'has_nocache_code' => false,
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_653fe90e8dd226_47285406')) {function content_653fe90e8dd226_47285406($_smarty_tpl) {?><!doctype html>
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
        <a id="homeIcon" href="index.html"><img src="images\EF.png" height="55" width="85"></a>
      </div>
      
      <div class = "col d-flex justify-content-center align-items-center">
        <input style = "outline: none;"class ="rounded-4 bg-black w-75 h-75 border-0 text-white" type="search" name="search" placeholder="Search posts" ></input>
      </div>

      <div class = "col d-flex justify-content-end align-items-center">
        <a class="btn btn-outline-light d-inline mx-1" href="login_template.php" role="button" >login</a>
        <a class="btn btn-outline-light d-inline mx-1" href="register_template.php" role="button" >register</a>
      </div>

    </div>

    <div class = "container-md my-5 mb-2 px-0 justify-content-end align-items-center">
      <div class="input-group">
        <textarea class="form-control bg-black text-white" placeholder="Post" aria-label="Post"></textarea>
        <button class = "btn btn-outline-light d-inline">Post</button>
      </div>
    </div>

    <?php  $_smarty_tpl->tpl_vars['posts'] = new Smarty_Variable; $_smarty_tpl->tpl_vars['posts']->_loop = false;
 $_from = $_smarty_tpl->tpl_vars['microposts']->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
foreach ($_from as $_smarty_tpl->tpl_vars['posts']->key => $_smarty_tpl->tpl_vars['posts']->value) {
$_smarty_tpl->tpl_vars['posts']->_loop = true;
?>
    <div class = "container-xl my-5 border border-1 p-1 rounded-2"> 
      <div class = "d-flex justify-content-start align-items-center p-2">
      <img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="60" width="60">
      <h1 class="ps-2 text-white"><?php echo $_smarty_tpl->tpl_vars['posts']->value['name'];?>
</h1>
      <h5 class="ps-3 pt-2 text-white">created at <?php echo $_smarty_tpl->tpl_vars['posts']->value['created_at'];?>
</h5><h6 class="ps-3 pt-2 text-white opacity-50">updated at <?php echo $_smarty_tpl->tpl_vars['posts']->value['updated_at'];?>
</h6>
      </div>
      <div class = "justify-content-start align-items-center p-2">
        <h5 class ="text-white"><?php echo $_smarty_tpl->tpl_vars['posts']->value['content'];?>
</h5>
      </div>
    </div>
  <?php } ?>

    <?php echo '<script'; ?>
 src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"><?php echo '</script'; ?>
>
  </body>

</html> <?php }} ?>
