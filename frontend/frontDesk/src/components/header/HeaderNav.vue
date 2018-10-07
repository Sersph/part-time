<template>
  <header class="header-nav-container">
    <div class="header-menu-list">
      <div>
        <transition name="opacity" mode="out-in">
          <a class="current-location-name" @click="showSelectCityContainer"
             v-show="this.$route.meta.showSelectCity">
            <span>{{ currentCity.name }}</span><i class="el-icon-arrow-down"></i>
          </a>
        </transition>
      </div>
      <div>
        <router-link to="/home">
          <span>首页</span>
        </router-link>
        <router-link to="/account/signIn" v-if="!userInfo.id">
          <span>登陆 / 注册</span>
        </router-link>
        <router-link class="control" :to="userInfo.type === 1 ? '/account/personal' : '/account/enterprise'" v-if="userInfo.id">
          <span>我的控制台</span>
        </router-link>
      </div>
    </div>
    <select-city ref="selectCity"/>
  </header>
</template>

<script>
import { mapState, mapActions } from 'vuex';
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
      'userInfo'
    ])
  },
  async mounted () {
    // 初始化用户信息
    await this.asyncInitUserInfo();
  },
  watch: {
    userInfo: {
      deep: true,
      handler (val) {
        console.log(val);
      }
    }
  },
  methods: {
    ...mapActions('account', [
      'asyncInitUserInfo'
    ]),
    showSelectCityContainer () {
      // 触发子组件显示
      this.$refs.selectCity.selectCityContainerVisibleFlag = true;
    }
  }
};
</script>

<style lang="scss">
  .header-nav-container {
    display: flex;
    justify-content: center;
    background-color: #409EFF;
    .header-menu-list {
      width: 83.33333%;
      display: flex;
      justify-content: space-between;
      div {
        display: flex;
        height: 60px;
        line-height: 60px;
        font-size: 16px;
        a {
          padding: 0 20px;
          color: #fff;
          cursor: pointer;
          border-bottom: 5px solid transparent;
          transition: border-bottom-color .3s, background-color .3s, opacity .7s;
        }
        a.current-location-name {
          span {
            margin-right: 10px;
          }
          i {
            width: 18px;
            height: 18px;
            margin-right: 0;
          }
        }
        a:hover {
          background: #66b1ff;
          border-color: #66b1ff;
        }
        a.active {
          border-bottom: 5px solid #1f6fc0;
        }
        a.control {
          background-color: #f56c6c;
        }
        a.control:hover {
          background: #f78989;
          border-color: #f73035;
          color: #fff;
        }
        a.control.active {
          border-bottom: 5px solid #f73035;
        }
      }
    }
  }
</style>
