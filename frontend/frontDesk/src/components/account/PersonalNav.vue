<template>
  <section class="personal-nav-container">
    <el-menu
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(personalMenuItem, index) in personalMenuList"
        :key="index"
        :index="personalMenuItem.index">
        <template slot="title">
          <i class="material-icons">{{ personalMenuItem.icon }}</i>
          <span>{{ personalMenuItem.title }}</span>
        </template>
        <el-menu-item
          v-for="(personalMenuChildrenItem, index) in personalMenuItem.children"
          :key="index"
          :route="personalMenuChildrenItem.route"
          :index="personalMenuChildrenItem.index">
          <span :class="{active: $route.path === personalMenuChildrenItem.route ? 'active': ''}"
          >{{ personalMenuChildrenItem.title }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'personalNav',
  data () {
    return {
      personalMenuList: [
        {
          index: '1',
          title: '我的兼职',
          icon: 'personal',
          children: [
            { index: '1-1', title: '我的申请', route: '/account/personal/partTimeApply' }
          ]
        },
        {
          index: '2',
          title: '账户设置',
          icon: 'view_list',
          children: [
            { index: '2-1', title: '修改简历', route: '/account/personal/resume' }
          ]
        }
      ]
    };
  }
};
</script>

<style lang="scss">
  .personal-nav-container {
    min-width: 250px;
    min-height: 50vh;
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
