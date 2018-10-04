<template>
  <header class="header-container">
    <el-col :span="20">
      <div class="header-menu-list">
        <div>
          <a class="current-location-name" @click="showSelectLocationContainer">
            <span>深圳</span><i class="el-icon-arrow-down"></i>
          </a>
        </div>
        <div>
          <router-link to="/home">
            <span>首页</span>
          </router-link>
          <router-link to="/account/signIn">
            <span>登陆 / 注册</span>
          </router-link>
        </div>
      </div>
    </el-col>
    <select-location ref="selectLocation"/>
  </header>
</template>

<script>
import { mapState } from 'vuex';
import SelectLocation from '@/components/location/SelectLocation';

export default {
  name: 'HeaderNav',
  components: {
    SelectLocation
  },
  computed: {
    ...mapState('location', [
      'currentLocation'
    ])
  },
  methods: {
    showSelectLocationContainer () {
      // 触发子组件显示
      this.$refs.selectLocation.selectLocationContainerVisibleFlag = true;
    }
  }
};
</script>

<style lang="scss">
  .header-container {
    display: flex;
    justify-content: center;
    background-color: #409EFF;
    .header-menu-list {
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
      }
    }
  }
</style>
