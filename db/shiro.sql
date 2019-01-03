-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info (
  uid int(11) NOT NULL AUTO_INCREMENT,
  username varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  name varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  password varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  salt varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  state int(11) DEFAULT NULL,
  PRIMARY KEY (uid)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO user_info VALUES ('1', 'admin', '管理员', '9f440614296812d69da6e1549ba5d641', 'shiro', '0');
INSERT INTO user_info VALUES ('2', 'zhangsan', '客户', '9f440614296812d69da6e1549ba5d641', 'shiro', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
  id int(11) NOT NULL AUTO_INCREMENT,
  available tinyint(4) DEFAULT NULL,
  description varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  role varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role VALUES ('1', '0', '管理员', 'admin');
INSERT INTO sys_role VALUES ('2', '0', 'VIP会员', 'vip');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_permission;
CREATE TABLE sys_permission (
  id int(11) NOT NULL AUTO_INCREMENT,
  available tinyint(4) DEFAULT NULL,
  name varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  parent_id int(11) DEFAULT NULL,
  parent_ids varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  permission varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  resource_type varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  url varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO sys_permission VALUES ('1', '0', '用户管理', '0', '0/', 'userInfo:view', 'menu', 'userInfo/userList');
INSERT INTO sys_permission VALUES ('2', '0', '用户添加', '1', '0/1', 'userInfo:add', 'button', 'userInfo/userAdd');
INSERT INTO sys_permission VALUES ('3', '0', '用户删除', '1', '0/1', 'userInfo:del', 'button', 'userInfo/userDel');
INSERT INTO sys_permission VALUES ('4', '0', '用户所有权限', '0', '0/', 'userInfo:*', 'menu', null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role (
  role_id int(11) NOT NULL,
  uid int(11) NOT NULL,
  PRIMARY KEY (role_id,uid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('1', '1');
INSERT INTO sys_user_role VALUES ('2', '2');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS sys_role_permission;
CREATE TABLE sys_role_permission (
  permission_id int(11) NOT NULL,
  role_id int(11) NOT NULL,
  PRIMARY KEY (permission_id,role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO sys_role_permission VALUES ('1', '1');
INSERT INTO sys_role_permission VALUES ('2', '1');
INSERT INTO sys_role_permission VALUES ('4', '2');
