<template>
  <section class="profile-nav-container">
    <el-menu
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(enterpriseMenuItem, index) in enterpriseMenuList"
        :key="index"
        :index="enterpriseMenuItem.index">
        <template slot="title">
          <i class="material-icons">{{ enterpriseMenuItem.icon }}</i>
          <span>{{ enterpriseMenuItem.title }}</span>
        </template>
        <el-menu-item
          v-for="(enterpriseMenuChildItem, index) in enterpriseMenuItem.children"
          :key="index"
          :route="enterpriseMenuChildItem.route"
          :index="enterpriseMenuChildItem.index">
          <span :class="{active: $route.path === enterpriseMenuChildItem.route ? 'active': ''}"
          >{{ enterpriseMenuChildItem.title }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'EnterpriseNav',
  data () {
    return {
      enterpriseMenuList: [
        {
          index: '1',
          title: '我的兼职',
          icon: 'personal',
          children: [
            { index: '1-1', title: '我的发布', route: '/account/enterprise/partTimePost' },
            { index: '1-2', title: '我的招聘', route: '/account/enterprise/partTimeRecruitment' }
          ]
        },
        {
          index: '2',
          title: '账户设置',
          icon: 'view_list',
          children: [
            { index: '2-1', title: '企业认证', route: '/account/enterprise/certification' }
          ]
        }
      ]
    };
  }
};
</script>

<style lang="scss">
  .profile-nav-container {
    min-width: 250px;
    height: 50vh;
    .el-menu {
      height: 100%;
    }
    .el-menu-item {
      padding-left: 55px !important;
    }
    .material-icons {
      width: 25px;
      height: 25px;
      margin-right: 10px;
    }
    .active {
      color: #409EFF;
    }
  }
</style>
