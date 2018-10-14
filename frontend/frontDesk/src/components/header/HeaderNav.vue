<template>
  <header class="header-nav-container">
    <el-menu
      mode="horizontal"
      background-color="#409EFF"
      active-text-color="#fff">
      <transition name="opacity" mode="out-in">
        <el-menu-item index="1" class="current-location-name"
                      @click="$refs.selectCity.selectCityContainerVisibleFlag = true"
                      v-show="this.$route.meta.showSelectCity">
          <a>{{ currentCity.name }}<i class="el-icon-arrow-down"></i></a>
        </el-menu-item>
      </transition>
      <el-menu-item index="2">
        <router-link to="/home">首页</router-link>
      </el-menu-item>
      <el-menu-item index="3" v-if="!accountInfo.id">
        <router-link to="/account/signIn">登陆/注册</router-link>
      </el-menu-item>
      <el-submenu index="5" :show-timeout="50" :hide-timeout="50" v-if="accountInfo.id">
        <template slot="title">我的</template>
        <el-menu-item index="5-1"
                      @click="$router.push(accountInfo.type === 1 ? '/account/personal' : '/account/enterprise')">
          <a>{{ accountInfo.type === 1 ? '个人中心' : '控制台' }}</a>
        </el-menu-item>
        <el-menu-item index="5-2" @click="signOut">
          <a>退出</a>
        </el-menu-item>
      </el-submenu>
    </el-menu>
    <select-city ref="selectCity"/>
  </header>
</template>

<script>
import { mapState } from 'vuex';
import api from '@/api';
import SelectCity from '@/components/location/SelectCity';

export default {
  name: 'HeaderNav',
  components: {
    SelectCity
  },
  computed: {
    ...mapState('location', [
      'currentCity'
    ]),
    ...mapState('account', [
      'accountInfo'
    ])
  },
  methods: {
    async signOut () {
      const result = await api.account.signOut();
      if (result.code === 0) {
        await this.asyncInitAccountInfo();
        this.$notify({
          message: '退出成功',
          position: 'bottom-left',
          duration: 1500,
          showClose: false
        });
        // 跳转到首页
        if (this.$route.path !== '/home') {
          this.$router.replace('/');
        }
      }
    }
  }
};
</script>

<style lang="scss">
  .header-nav-container {
    display: flex;
    justify-content: center;
    background-color: #409EFF;
    box-shadow: 0 2px 11px rgba(0, 0, 0, 0.2);
    .el-menu.el-menu--horizontal {
      border: none;
    }
    .el-menu {
      width: 83.33333%;
      text-align: right;
      & > li {
        float: none !important;
        display: inline-block;
        padding: 0;
        border: none !important;
        i {
          width: 12px;
          height: 12px;
          font-size: 13px;
          margin-left: 8px;
          color: #fff !important;
        }
        .el-icon-arrow-down {
          margin-top: -3px;
        }
        .el-submenu__title {
          border: none !important;
          color: #fff !important;
        }
        a {
          display: block;
          padding: 0 20px;
          color: #fff !important;
        }
      }
      & > li:first-child {
        float: left !important;
      }
    }
    .el-submenu.is-opened > .el-submenu__title .el-submenu__icon-arrow {
      transform: none;
    }
  }

  .el-menu--popup {
    min-width: 89px !important;
    a {
      color: #fff;
    }
  }
</style>
