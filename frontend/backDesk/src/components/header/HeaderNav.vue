<template>
  <section class="header-container">
    <transition name="slide-left" mode="out-in">
      <el-breadcrumb separator="/" v-show="opacityFlag">
        <el-breadcrumb-item
          v-for="(router, index) in currentRoute.matched"
          :key="index"
        >{{ router.meta.name }}
        </el-breadcrumb-item>
      </el-breadcrumb>
    </transition>
    <section class="user-action-container">
      <el-dropdown trigger="click">
      <span class="el-dropdown-link">
        <i class="material-icons">personal</i>
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>退出登陆</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </section>
  </section>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  name: 'HeaderNav',
  data () {
    return {
      opacityFlag: true,
      currentRoute: {}
    };
  },
  computed: {
    ...mapState('account', [
      'accountInfo'
    ])
  },
  watch: {
    $route: {
      handler (route) {
        // 刷新面包屑导航
        this.editCurrentRoute(route);
      }
    }
  },
  async mounted () {
    // 刷新面包屑导航
    this.editCurrentRoute(this.$route);
    // 初始化用户信息
    await this.asyncInitAccountInfo();
    // 判断登陆权限
    this.checkSignIn(this.$route);
  },
  methods: {
    ...mapActions('account', [
      'asyncInitAccountInfo'
    ]),
    editCurrentRoute (route) {
      this.opacityFlag = false;
      setTimeout(() => {
        this.currentRoute = route;
        this.opacityFlag = true;
      }, 300);
    },
    checkSignIn (route) {
      // 验证路由是否需要登陆才能访问
      if (route.meta.needSignIn) {
        if (!this.accountInfo.id) {
          // 未登录跳转登陆页面
          this.$router.push('/account/signIn');
        }
      }
    }
  }
};
</script>

<style lang="scss">
  .header-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 100%;
    .el-breadcrumb {
      padding: 23px 0;
    }
    .user-action-container {
      display: flex;
      align-items: center;
      .el-dropdown {
        .el-dropdown-link {
          display: flex;
          align-items: center;
          width: 100%;
          height: 100%;
          cursor: pointer;
          .material-icons {
            width: 40px;
            height: 40px;
            font-size: 40px;
          }
          .el-icon-arrow-down {
            margin-left: 0;
          }
        }
      }
    }
  }
</style>
