<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <title>RPlus快速开发运行平台</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta name="MobileOptimized" content="320"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/plugins/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/plugins/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/plugins/uniform/css/uniform.default.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/plugins/fuelux/css/tree-metronic.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/style-metronic.css"/>
    <link rel="stylesheet"
          href="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/css/datepicker.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/style.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/style-responsive.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/plugins.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/pages/tasks.css"/>
    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/themes/light.css"/>

    <link rel="stylesheet" href="${contextPath}/resources/public/assets/css/food.css"/>

    <link rel="shortcut icon" href="favicon.ico"/>
</head>
<body class="page-header-fixed">
<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="header-inner">
        <!-- BEGIN LOGO -->
        <a class="navbar-brand" href="index.html">
            <img src="/rplus/resources/rplus/images/logo_horizontal.png" alt="logo" class="img-responsive">
        </a>
        <!-- END LOGO -->
        <!-- BEGIN TOP NAVIGATION MENU -->
        <ul class="nav navbar-nav pull-right">
            <!-- BEGIN NOTIFICATION DROPDOWN -->
            <li class="dropdown" id="header_notification_bar"
                style="outline: 0px; box-shadow: rgba(102, 188, 230, 0) 0px 0px 13px; outline-offset: 20px;">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                   data-close-others="true">
                    <i class="fa fa-warning"></i>
                    <span class="badge">9</span>
                </a>
                <ul class="dropdown-menu extended notification">
                    <li>
                        <p>You have 14 new notifications</p>
                    </li>
                    <li>
                        <div class="slimScrollDiv"
                             style="position: relative; overflow: hidden; width: auto; height: 250px;">
                            <ul class="dropdown-menu-list scroller"
                                style="height: 250px; overflow: hidden; width: auto;">
                                <li>
                                    <a href="#">
                                        <span class="label label-sm label-icon label-success"><i class="fa fa-plus"></i></span>
                                        New user registered.
                                        <span class="time">Just now</span>
                                    </a>
                                </li>
                            </ul>
                            <div class="slimScrollBar"
                                 style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: block; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; z-index: 99; right: 1px; background: rgb(161, 178, 189);"></div>
                            <div class="slimScrollRail"
                                 style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-top-left-radius: 7px; border-top-right-radius: 7px; border-bottom-right-radius: 7px; border-bottom-left-radius: 7px; opacity: 0.2; z-index: 90; right: 1px; background: rgb(51, 51, 51);"></div>
                        </div>
                    </li>
                    <li class="external">
                        <a href="#">See all notifications <i class="m-icon-swapright"></i></a>
                    </li>
                </ul>
            </li>
            <!-- END NOTIFICATION DROPDOWN -->
            <!-- BEGIN USER LOGIN DROPDOWN -->
            <li class="dropdown user">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                   data-close-others="true">
                    <img alt="" src="assets/img/avatar1_small.jpg">
                    <span class="username">系统管理员</span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="extra_profile.html"><i class="fa fa-user"></i> 个人信息</a></li>
                    <li><a href="page_calendar.html"><i class="fa fa-calendar"></i> My Calendar</a></li>
                    <li><a href="inbox.html"><i class="fa fa-envelope"></i> My Inbox <span
                            class="badge badge-danger">3</span></a></li>
                    <li><a href="#"><i class="fa fa-tasks"></i> My Tasks <span class="badge badge-success">7</span></a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="javascript:;" id="trigger_fullscreen"><i class="fa fa-move"></i> 全屏</a></li>
                    <li><a href="extra_lock.html"><i class="fa fa-lock"></i> 锁屏</a></li>
                    <li><a href="login.html"><i class="fa fa-key"></i> 退出</a></li>
                </ul>
            </li>
            <!-- END USER LOGIN DROPDOWN -->
        </ul>
        <!-- END TOP NAVIGATION MENU -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>

<div class="clearfix"></div>
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- BEGIN SIDEBAR -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <ul class="page-sidebar-menu">
            <li>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
                <div class="sidebar-toggler hidden-phone"></div>
                <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            </li>
            <li>
                <!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
                <form class="sidebar-search" action="extra_search.html" method="POST">
                    <div class="form-container">
                        <div class="input-box">
                            <a href="javascript:;" class="remove"></a>
                            <input type="text" placeholder="Search...">
                            <input type="button" class="submit" value=" ">
                        </div>
                    </div>
                </form>
                <!-- END RESPONSIVE QUICK SEARCH FORM -->
            </li>
            <li class="start active ">
                <a href="index.html">
                    <i class="fa fa-home"></i>
                    <span class="title">Dashboard</span>
                    <span class="selected"></span>
                </a>
            </li>
            <li class="">
                <a href="index_horizontal_menu.html">
                    <i class="fa fa-briefcase"></i>
                    <span class="title">Dashboard 2</span>
                </a>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-cogs"></i>
                    <span class="title">Layouts</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="layout_session_timeout.html">
                            <span class="badge badge-roundless badge-warning">new</span>Session Timeout</a>
                    </li>
                    <li>
                        <a href="layout_idle_timeout.html">
                            <span class="badge badge-roundless badge-important">new</span>User Idle Timeout</a>
                    </li>
                    <li>
                        <a href="layout_language_bar.html">
                            Language Switch Bar</a>
                    </li>
                    <li>
                        <a href="layout_horizontal_sidebar_menu.html">
                            Horizontal &amp; Sidebar Menu</a>
                    </li>
                    <li>
                        <a href="layout_horizontal_menu1.html">
                            Horizontal Menu 1</a>
                    </li>
                    <li>
                        <a href="layout_horizontal_menu2.html">
                            Horizontal Menu 2</a>
                    </li>
                    <li>
                        <a href="layout_sidebar_toggler_on_header.html">
                            <span class="badge badge-roundless badge-warning">new</span>Sidebar Toggler On Header</a>
                    </li>
                    <li>
                        <a href="layout_sidebar_fixed.html">
                            Sidebar Fixed Page</a>
                    </li>
                    <li>
                        <a href="layout_sidebar_closed.html">
                            Sidebar Closed Page</a>
                    </li>
                    <li>
                        <a href="layout_disabled_menu.html">
                            Disabled Menu Links</a>
                    </li>
                    <li>
                        <a href="layout_blank_page.html">
                            Blank Page</a>
                    </li>
                    <li>
                        <a href="layout_boxed_page.html">
                            Boxed Page</a>
                    </li>
                    <li>
                        <a href="layout_boxed_not_responsive.html">
                            Non-Responsive Boxed Layout</a>
                    </li>
                    <li>
                        <a href="layout_promo.html">
                            Promo Page</a>
                    </li>
                    <li>
                        <a href="layout_email.html">
                            Email Templates</a>
                    </li>
                    <li>
                        <a href="layout_ajax.html">
                            Content Loading via Ajax</a>
                    </li>
                </ul>
            </li>
            <li class="tooltips" data-placement="right" data-original-title="Frontend&nbsp;Theme For&nbsp;Metronic&nbsp;Admin">
                <a href="http://keenthemes.com/preview/index.php?theme=metronic_frontend" target="_blank">
                    <i class="fa fa-gift"></i>
                    <span class="title">Frontend Theme</span>
                </a>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-bookmark-o"></i>
                    <span class="title">UI Features</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="ui_general.html">
                            General</a>
                    </li>
                    <li>
                        <a href="ui_buttons.html">
                            Buttons &amp; Icons</a>
                    </li>
                    <li>
                        <a href="ui_typography.html">
                            Typography</a>
                    </li>
                    <li>
                        <a href="ui_modals.html">
                            Modals</a>
                    </li>
                    <li>
                        <a href="ui_extended_modals.html">
                            Extended Modals</a>
                    </li>
                    <li>
                        <a href="ui_tabs_accordions_navs.html">
                            Tabs, Accordions &amp; Navs</a>
                    </li>
                    <li>
                        <a href="ui_datepaginator.html">
                            <span class="badge badge-roundless badge-success">new</span>Date Paginator</a>
                    </li>
                    <li>
                        <a href="ui_bootbox.html">
                            <span class="badge badge-roundless badge-important">new</span>Bootbox Dialogs</a>
                    </li>
                    <li>
                        <a href="ui_tiles.html">
                            Tiles</a>
                    </li>
                    <li>
                        <a href="ui_toastr.html">
                            <span class="badge badge-roundless badge-warning">new</span>Toastr Notifications</a>
                    </li>
                    <li>
                        <a href="ui_tree.html">
                            Tree View</a>
                    </li>
                    <li>
                        <a href="ui_nestable.html">
                            Nestable List</a>
                    </li>
                    <li>
                        <a href="ui_ion_sliders.html">
                            Ion Range Sliders</a>
                    </li>
                    <li>
                        <a href="ui_noui_sliders.html">
                            <span class="badge badge-roundless badge-success">new</span>NoUI Range Sliders</a>
                    </li>
                    <li>
                        <a href="ui_jqueryui_sliders.html">
                            jQuery UI Sliders</a>
                    </li>
                    <li>
                        <a href="ui_knob.html">
                            Knob Circle Dials</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-table"></i>
                    <span class="title">Form Stuff</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="form_controls.html">
                            Form Controls</a>
                    </li>
                    <li>
                        <a href="form_layouts.html">
                            Form Layouts</a>
                    </li>
                    <li>
                        <a href="form_component.html">
                            Form Components</a>
                    </li>
                    <li>
                        <a href="form_editable.html">
                            <span class="badge badge-roundless badge-warning">new</span>Form X-editable</a>
                    </li>
                    <li>
                        <a href="form_wizard.html">
                            Form Wizard</a>
                    </li>
                    <li>
                        <a href="form_validation.html">
                            Form Validation</a>
                    </li>
                    <li>
                        <a href="form_image_crop.html">
                            <span class="badge badge-roundless badge-important">new</span>Image Cropping</a>
                    </li>
                    <li>
                        <a href="form_fileupload.html">
                            Multiple File Upload</a>
                    </li>
                    <li>
                        <a href="form_dropzone.html">
                            Dropzone File Upload</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-sitemap"></i>
                    <span class="title">Pages</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="page_portfolio.html">
                            <i class="fa fa-briefcase"></i>
                            <span class="badge badge-warning badge-roundless">new</span>Portfolio</a>
                    </li>
                    <li>
                        <a href="page_timeline.html">
                            <i class="fa fa-clock-o"></i>
                            <span class="badge badge-info">4</span>Timeline</a>
                    </li>
                    <li>
                        <a href="page_coming_soon.html">
                            <i class="fa fa-cogs"></i>
                            Coming Soon</a>
                    </li>
                    <li>
                        <a href="page_blog.html">
                            <i class="fa fa-comments"></i>
                            Blog</a>
                    </li>
                    <li>
                        <a href="page_blog_item.html">
                            <i class="fa fa-font"></i>
                            Blog Post</a>
                    </li>
                    <li>
                        <a href="page_news.html">
                            <i class="fa fa-coffee"></i>
                            <span class="badge badge-success">9</span>News</a>
                    </li>
                    <li>
                        <a href="page_news_item.html">
                            <i class="fa fa-bell"></i>
                            News View</a>
                    </li>
                    <li>
                        <a href="page_about.html">
                            <i class="fa fa-group"></i>
                            About Us</a>
                    </li>
                    <li>
                        <a href="page_contact.html">
                            <i class="fa fa-envelope-o"></i>
                            Contact Us</a>
                    </li>
                    <li>
                        <a href="page_calendar.html">
                            <i class="fa fa-calendar"></i>
                            <span class="badge badge-important">14</span>Calendar</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-gift"></i>
                    <span class="title">Extra</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="extra_profile.html">
                            User Profile</a>
                    </li>
                    <li>
                        <a href="extra_lock.html">
                            Lock Screen</a>
                    </li>
                    <li>
                        <a href="extra_faq.html">
                            FAQ</a>
                    </li>
                    <li>
                        <a href="inbox.html">
                            <span class="badge badge-important">4</span>Inbox</a>
                    </li>
                    <li>
                        <a href="extra_search.html">
                            Search Results</a>
                    </li>
                    <li>
                        <a href="extra_invoice.html">
                            Invoice</a>
                    </li>
                    <li>
                        <a href="extra_pricing_table.html">
                            Pricing Tables</a>
                    </li>
                    <li>
                        <a href="extra_404_option1.html">
                            404 Page Option 1</a>
                    </li>
                    <li>
                        <a href="extra_404_option2.html">
                            404 Page Option 2</a>
                    </li>
                    <li>
                        <a href="extra_404_option3.html">
                            404 Page Option 3</a>
                    </li>
                    <li>
                        <a href="extra_500_option1.html">
                            500 Page Option 1</a>
                    </li>
                    <li>
                        <a href="extra_500_option2.html">
                            500 Page Option 2</a>
                    </li>
                </ul>
            </li>
            <li>
                <a class="active" href="javascript:;">
                    <i class="fa fa-leaf"></i>
                    <span class="title">3 Level Menu</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;">
                            Item 1
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li><a href="#">Sample Link 1</a></li>
                            <li><a href="#">Sample Link 2</a></li>
                            <li><a href="#">Sample Link 3</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;">
                            Item 1
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li><a href="#">Sample Link 1</a></li>
                            <li><a href="#">Sample Link 1</a></li>
                            <li><a href="#">Sample Link 1</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            Item 3
                        </a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="fa fa-folder-open"></i>
                    <span class="title">4 Level Menu</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="javascript:;">
                            <i class="fa fa-cogs"></i>
                            Item 1
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a href="javascript:;">
                                    <i class="fa fa-user"></i>
                                    Sample Link 1
                                    <span class="arrow"></span>
                                </a>
                                <ul class="sub-menu">
                                    <li><a href="#"><i class="fa fa-remove"></i> Sample Link 1</a></li>
                                    <li><a href="#"><i class="fa fa-pencil"></i> Sample Link 1</a></li>
                                    <li><a href="#"><i class="fa fa-edit"></i> Sample Link 1</a></li>
                                </ul>
                            </li>
                            <li><a href="#"><i class="fa fa-user"></i>  Sample Link 1</a></li>
                            <li><a href="#"><i class="fa fa-external-link"></i>  Sample Link 2</a></li>
                            <li><a href="#"><i class="fa fa-bell"></i>  Sample Link 3</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <i class="fa fa-globe"></i>
                            Item 2
                            <span class="arrow"></span>
                        </a>
                        <ul class="sub-menu">
                            <li><a href="#"><i class="fa fa-user"></i>  Sample Link 1</a></li>
                            <li><a href="#"><i class="fa fa-external-link"></i>  Sample Link 1</a></li>
                            <li><a href="#"><i class="fa fa-bell"></i>  Sample Link 1</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa-folder-open"></i>
                            Item 3
                        </a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-user"></i>
                    <span class="title">Login Options</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="login.html">
                            Login Form 1</a>
                    </li>
                    <li>
                        <a href="login_soft.html">
                            Login Form 2</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-th"></i>
                    <span class="title">Data Tables</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="table_basic.html">
                            Basic Datatables</a>
                    </li>
                    <li>
                        <a href="table_responsive.html">
                            Responsive Datatables</a>
                    </li>
                    <li>
                        <a href="table_managed.html">
                            Managed Datatables</a>
                    </li>
                    <li>
                        <a href="table_editable.html">
                            Editable Datatables</a>
                    </li>
                    <li>
                        <a href="table_advanced.html">
                            Advanced Datatables</a>
                    </li>
                    <li>
                        <a href="table_ajax.html">
                            Ajax Datatables</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-file-text"></i>
                    <span class="title">Portlets</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="portlet_general.html">
                            General Portlets</a>
                    </li>
                    <li>
                        <a href="portlet_draggable.html">
                            Draggable Portlets</a>
                    </li>
                </ul>
            </li>
            <li class="">
                <a href="javascript:;">
                    <i class="fa fa-map-marker"></i>
                    <span class="title">Maps</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="maps_google.html">
                            Google Maps</a>
                    </li>
                    <li>
                        <a href="maps_vector.html">
                            Vector Maps</a>
                    </li>
                </ul>
            </li>
            <li class="last ">
                <a href="charts.html">
                    <i class="fa fa-bar-chart-o"></i>
                    <span class="title">Visual Charts</span>
                </a>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
    <!-- END SIDEBAR -->
    <!-- BEGIN PAGE -->
    <div class="page-content">
        this is page .
    </div>
    <!-- END PAGE -->
    <!-- BEGIN CONTAINER -->
</div>
<!-- BEGIN FOOTER -->
<div class="footer">
    <div class="footer-inner">
        2014 © Rexen RPlus.
    </div>
    <div class="footer-tools">
			<span class="go-top">
			<i class="fa fa-angle-up"></i>
			</span>
    </div>
</div>
<!-- END FOOTER -->

<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="/rplus/resources/public/assets/plugins/respond.min.js"></script><![endif]-->
<script type="text/javascript" src="/rplus/resources/public/javascripts/lib/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="/rplus/resources/public/javascripts/lib/underscore-min.js"></script>
<script src="/rplus/resources/public/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/rplus/resources/public/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
<script src="/rplus/resources/public/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/rplus/resources/public/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/rplus/resources/public/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="/rplus/resources/public/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
<!-- END CORE PLUGINS -->


<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/backbone-min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/backbone-validation-min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/backbone.wreqr.min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/backbone.babysitter.min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/backbone.marionette.min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/handlebars-v1.3.0.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/lib/handlebars-helper.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/assets/plugins/fuelux/js/tree.min.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js"></script>--%>


<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/jui.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/models.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/templates.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/views.js"></script>--%>
<%--<script type="text/javascript" src="${contextPath}/resources/public/javascripts/resource.js"></script>--%>


</body>
</html>
