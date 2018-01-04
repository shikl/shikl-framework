-- --------------------------------------------------------
-- 主机:                           172.17.2.122
-- 服务器版本:                        5.6.10 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- 正在导出表  rplus_web.r_apps 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `r_apps` DISABLE KEYS */;
INSERT INTO `r_apps` (`id`, `apppath`, `mainPage`, `name`) VALUES
	('rplus', '/rplus', 'rpuls_web', 'rplus');
/*!40000 ALTER TABLE `r_apps` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_app_relate 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_app_relate` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_app_relate` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_dictionary 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_dictionary` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_dictionary` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_fields 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_fields` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_fields` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_idgenerator 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `r_idgenerator` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_idgenerator` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_operate 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `r_operate` DISABLE KEYS */;
INSERT INTO `r_operate` (`id`, `name`, `uri`) VALUES
	('B', '浏览', '/'),
	('C', '增加', '/save'),
	('D', '删除', '/delete'),
	('L', '查询', '/list'),
	('U', '编辑', '/update');
/*!40000 ALTER TABLE `r_operate` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_options 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_options` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_options` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_organization 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `r_organization` DISABLE KEYS */;
INSERT INTO `r_organization` (`id`, `isleaf`, `name`, `parentid`, `appid`) VALUES
	('_root', 1, '根机构', NULL, 'rplus');
/*!40000 ALTER TABLE `r_organization` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_permission` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_resource 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `r_resource` DISABLE KEYS */;
INSERT INTO `r_resource` (`id`, `isleaf`, `name`, `parentgroup`, `path`, `sort`, `type`, `appid`) VALUES
	('main', 0, '主页', '_root', '/main.jsp', NULL, NULL, 'rplus'),
	('sa_001', 0, '资源管理', '_root', '/resource', NULL, NULL, 'rplus'),
	('sa_002', 0, '角色管理', '_root', '/role', NULL, NULL, 'rplus'),
	('sa_003', 0, '用户管理', '_root', '/user', NULL, NULL, 'rplus'),
	('_root', 0, '资源根', '', '/', NULL, NULL, 'rplus');
/*!40000 ALTER TABLE `r_resource` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_role 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `r_role` DISABLE KEYS */;
INSERT INTO `r_role` (`id`, `isleaf`, `name`, `parentid`, `appid`) VALUES
	('admin', 1, '系统管理员', '_root', 'rplus'),
	('_root', 1, '_root', NULL, 'rplus');
/*!40000 ALTER TABLE `r_role` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_role_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_role_permission` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_tables 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_tables` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_tables` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `r_user` DISABLE KEYS */;
INSERT INTO `r_user` (`id`, `age`, `contact`, `credentials`, `credentialsExpired`, `email`, `user_expired`, `user_locked`, `marital`, `nation`, `password`, `politics`, `realname`, `sex`, `username`, `appid`, `birthday`, `phone`) VALUES
	('admin', NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, 'e10adc3949ba59abbe56e057f20f883e', NULL, NULL, NULL, 'admin', 'rplus', NULL, NULL);
/*!40000 ALTER TABLE `r_user` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_userdetail 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_userdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_userdetail` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_user_organization 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_user_organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_organization` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_user_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `r_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `r_user_permission` ENABLE KEYS */;

-- 正在导出表  rplus_web.r_user_role 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `r_user_role` DISABLE KEYS */;
INSERT INTO `r_user_role` (`roleid`, `userid`) VALUES
	('admin', 'admin');
/*!40000 ALTER TABLE `r_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
