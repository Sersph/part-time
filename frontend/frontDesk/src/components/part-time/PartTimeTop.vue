<template>
  <section class="part-time-header-container">
    <el-row>
      <el-col :span="6" class="part-time-logo">
        <img src="../../assets/logo.png"/>
      </el-col>
      <el-col :span="12" class="part-time-search">
        <el-input placeholder="搜索兼职" v-model="keyword">
          <el-button slot="append" @click="searchPartTime"><i class="material-icons">search</i></el-button>
        </el-input>
      </el-col>
      <el-col :span="6" class="part-time-tooltip">
        <router-link to="/account/enterprise/partTime/partTimePost/add">
          <el-button type="danger">发布兼职</el-button>
        </router-link>
      </el-col>
    </el-row>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import routerUtils from '@/utils/router';

export default {
  name: 'PartTimeHeader',
  data () {
    return {
      keyword: ''
    };
  },
  computed: {
    ...mapState('partTime', [
      'partTimeSearchCondition'
    ])
  },
  watch: {
    partTimeSearchCondition: {
      deep: true,
      handler (partTimeSearchCondition) {
        // init 搜索关键字
        if (this.keyword !== partTimeSearchCondition.keyword) {
          this.keyword = partTimeSearchCondition.keyword;
        }
      }
    }
  },
  methods: {
    async searchPartTime () {
      // url 更新兼职搜索关键字
      this.$router.push({
        path: this.$router.pah,
        query: routerUtils.getNewPartTimeSearchConditionUrlParams(this.$route.query, {
          keyword: this.keyword.trim()
        })
      });
    }
  }
};
</script>

<style lang="scss">
  .part-time-header-container {
    padding: 50px 0 50px 20px;
    .el-row {
      display: flex;
      align-items: center;
      .part-time-logo {
        display: flex;
        align-items: flex-start;
        height: 50px;
        img {
          width: 150px;
          height: 50px;
          max-width: 100%;
        }
      }
      .part-time-search {
        input {
          height: 45px;
          font-size: 15px;
          box-shadow: 0 5px 20px rgba(0, 0, 0, 0.2);
        }
        .el-input-group__append {
          font-size: 20px;
          background-color: #409EFF;
          border-color: #409EFF;
          color: #fff;
          .material-icons {
            position: relative;
            top: 0.1em;
            width: 25px;
            height: 25px;
          }
        }
        .el-input-group__append:hover {
          background: #66b1ff;
          border-color: #66b1ff;
          color: #fff;
        }
        .el-input-group__append:active {
          background: #3a8ee6;
          border-color: #3a8ee6;
          color: #fff;
        }
      }
      .part-time-tooltip {
        text-align: right;
        button {
          height: 45px;
          font-size: 15px;
        }
      }
    }
  }
</style>
