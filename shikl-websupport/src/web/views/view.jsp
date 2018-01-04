<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="${contextPath}/resources/public/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${contextPath}/resources/public/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL PLUGIN STYLES -->
    <link href="${contextPath}/resources/public/assets/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" rel="stylesheet"
          type="text/css"/>
    <!-- END PAGE LEVEL PLUGIN STYLES -->
    <!-- BEGIN THEME STYLES -->
    <link href="${contextPath}/resources/public/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/css/themes/light.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="${contextPath}/resources/public/assets/css/custom.css" rel="stylesheet" type="text/css"/>
    <link href="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${contextPath}/resources/public/stylesheets/custom.css"/>
</head>
<body>
${html}

<!-- END THEME STYLES -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${contextPath}/resources/public/assets/plugins/respond.min.js"></script>
<script src="${contextPath}/resources/public/assets/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="${contextPath}/resources/public/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${contextPath}/resources/public/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js"
        type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${contextPath}/resources/public/assets/plugins/bootstrap-daterangepicker/moment.min.js"
        type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/plugins/bootstrap-daterangepicker/daterangepicker.js"
        type="text/javascript"></script>
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${contextPath}/resources/public/assets/scripts/app.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/scripts/dataTable.js" type="text/javascript"></script>

<script type="text/javascript"
        src="${contextPath}/resources/public/assets/plugins/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript"
        src="${contextPath}/resources/public/assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
<script type="text/javascript"
        src="${contextPath}/resources/public/assets/plugins/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript"
        src="${contextPath}/resources/public/assets/plugins/jquery-validation/dist/form.validation.js"></script>
<script src="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script> 
<script src="${contextPath}/resources/public/assets/plugins/bootstrap-datepicker/js/locales/bootstrap-datepicker.zh-CN.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/scripts/app.js" type="text/javascript"></script>
<script src="${contextPath}/resources/public/assets/scripts/dataTable.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and core plugins
    });
</script>
<!-- END JAVASCRIPTS -->

</body>
</html>
