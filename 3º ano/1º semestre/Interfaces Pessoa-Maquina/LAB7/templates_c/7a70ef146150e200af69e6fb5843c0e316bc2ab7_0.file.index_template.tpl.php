<?php
/* Smarty version 3.1.33-dev-7, created on 2023-11-05 15:27:05
  from '/users/a76943/public_html/LAB5/templates/index_template.tpl' */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.33-dev-7',
  'unifunc' => 'content_6547a639494ed7_75689182',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '7a70ef146150e200af69e6fb5843c0e316bc2ab7' => 
    array (
      0 => '/users/a76943/public_html/LAB5/templates/index_template.tpl',
      1 => 1699194446,
      2 => 'file',
    ),
  ),
  'includes' => 
  array (
  ),
),false)) {
function content_6547a639494ed7_75689182 (Smarty_Internal_Template $_smarty_tpl) {
?><!doctype html>
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

      <?php if ($_smarty_tpl->tpl_vars['isLoggedIn']->value) {?>
        <div class = "col d-flex justify-content-end align-items-center">
          <h4 class = "text-white pe-3 pt-1">Welcome back!</h4>
          <img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="45" width="45">
          <h4 class = "text-white px-2 pt-1"><?php echo $_smarty_tpl->tpl_vars['name']->value;?>
</h4>
          <a class="btn btn-outline-light d-inline mx-1" href="logout_action.php" role="button">Logout</a>
        </div>
      <?php } else { ?>
        <div class = "col d-flex justify-content-end align-items-center">
          <a class="btn btn-outline-light d-inline mx-1" href="login.php" role="button" >Login</a>
          <a class="btn btn-outline-light d-inline mx-1" href="register.php" role="button" >Register</a>
        </div>
      <?php }?>

    </div>

    <div class = "container-md my-5 mb-2 px-0 justify-content-end align-items-center">
      <div class="input-group">
        <textarea class="form-control bg-black text-white" placeholder="Post" aria-label="Post"></textarea>
        <button class = "btn btn-outline-light d-inline">Post</button>
      </div>
    </div>

  <?php
$_from = $_smarty_tpl->smarty->ext->_foreach->init($_smarty_tpl, $_smarty_tpl->tpl_vars['microposts']->value, 'posts');
if ($_from !== null) {
foreach ($_from as $_smarty_tpl->tpl_vars['posts']->value) {
?>
    <div class = "container-xl my-5 border border-1 p-1 rounded-2"> 
      <div class = "col d-flex justify-content-start align-items-center p-2">
        <img class = "border-2 border-light rounded-2" src="images\silly_cat.jpg" height="60" width="60">
        <h1 class="ps-2 text-white"><?php echo $_smarty_tpl->tpl_vars['posts']->value['name'];?>
</h1>
        <h5 class="ps-3 pt-2 text-white">created at <?php echo $_smarty_tpl->tpl_vars['posts']->value['created_at'];?>
</h5><h6 class="ps-3 pt-2 text-white opacity-50">updated at <?php echo $_smarty_tpl->tpl_vars['posts']->value['updated_at'];?>
</h6>
        <?php if ($_smarty_tpl->tpl_vars['posts']->value['user_id'] == $_smarty_tpl->tpl_vars['id']->value) {?>
          <div class="col d-flex container-sm justify-content-end align-items-right mb-2">
            <a class="btn btn-sm btn-outline-light d-inline mx-1 float-right" href=# role="button ">Update</a>
          </div>
        <?php }?>
      </div>
      <div class = "justify-content-start align-items-center p-2">
        <h5 class ="text-white"><?php echo $_smarty_tpl->tpl_vars['posts']->value['content'];?>
</h5>
      </div>
    </div>
  <?php
}
}
$_smarty_tpl->smarty->ext->_foreach->restore($_smarty_tpl, 1);?>

    <?php echo '<script'; ?>
 src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"><?php echo '</script'; ?>
>
  </body>

</html> <?php }
}
