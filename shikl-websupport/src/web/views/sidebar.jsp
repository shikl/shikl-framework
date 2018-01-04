<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<sidebar>
    <%--<div class="page-sidebar navbar-collapse" id="page-sidebar">--%>
    <ul class="page-sidebar-menu sidebar-menu" id="sidebar-menu" data-url="${contextPath}/nav/" data-current="1">
        <li class="active"><a href="/"><i class="fa fa-home"></i><span class="title">首页</span></a></li>
        <li><a href="${contextPath}/resource"><i class="fa fa-inbox"></i><span class="title">资源管理</span></a></li>
        <li><a href="${contextPath}/role"><i class="fa fa-group"></i><span class="title">角色管理</span></a></li>
        <li><a href="${contextPath}/user"><i class="fa fa-user"></i><span class="title">用户管理</span></a></li>
        <li><a href="${contextPath}/org"><i class="fa fa-sitemap"></i><span class="title">机构管理</span></a></li>
    </ul>    
    <%--</div>--%>
</sidebar>
<script type="text/javascript">
$( document ).ready(
  var sideMenu, template;

  template = '<ul class="page-sidebar-menu">\n  {{#each}}\n  <li>\n    <a href="{{url}}">  \n      <i class="fa {{icon}}"></i> \n      <span class="title">{{title}}</span> \n    </a> \n  </li>\n  {{/each}}\n</ul>';

  sideMenu = Handlebars.compile(template);

  $('.sidebar-menu').each(function(index, menu) {
    var current, url;
    url = $(menu).data('url');
    current = $(menu).data('current');
    return $.getJSON(url, function(data, textStatus) {
      var newMenu;
      newMenu = $(sideMenu(data));
      newMenu.find('> li').eq(current).addClass('active');
      $(menu).after(newMenu);
      return $(menu).remove();
    });
  });
)
</script>
<sitemesh:write property='breadcrumb'/>
<sitemesh:write property='body'/>
