<template>
  <section class="profile-nav-container">
    <el-menu
      :default-openeds="['1', '2']"
      :router="true">
      <el-submenu
        v-for="(profileMenuItem, index) in profileMenuList"
        :key="index"
        :index="profileMenuItem.index">
        <template slot="title">
          <i class="material-icons">{{ profileMenuItem.icon }}</i>
          <span>{{ profileMenuItem.title }}</span>
        </template>
        <el-menu-item
          v-for="(profileMenuChildrenItem, index) in profileMenuItem.children"
          :key="index"
          :route="profileMenuChildrenItem.route"
          :index="profileMenuChildrenItem.index">
          <span
            :class="{active: $route.path === profileMenuChildrenItem.route ? 'active': ''}"
          >{{ profileMenuChildrenItem.title }}</span>
        </el-menu-item>
      </el-submenu>
    </el-menu>
  </section>
</template>

<script>
export default {
  name: 'ProfileNav',
  data () {
    return {
      profileMenuList: [
        {
          index: '1',
          title: '我的兼职',
          icon: 'personal',
          children: [
            { index: '1-1', title: '我的申请', route: '/account/profile/apply' }
          ]
        },
        {
          index: '2',
          title: '账户设置',
          icon: 'view_list',
          children: [
            { index: '2-1', title: '修改简历', route: '/account/profile/resume' },
            { index: '2-1', title: '修改密码', route: '/account/profile/password' }
          ]
        }
      ]
    };
  }
};
</script>

<style lang="scss">
  .profile-nav-container {
    width: 250px;
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
