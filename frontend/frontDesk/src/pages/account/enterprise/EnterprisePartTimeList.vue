<template>
  <section class="enterprise-part-time-post-list-container">
    <el-col class="part-time-post-tooltip">
      <router-link to="/account/enterprise/partTime/add">
        <el-button type="danger">发布兼职</el-button>
      </router-link>
    </el-col>
    <el-table
      :data="enterprisePartTimePostList"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        prop="date"
        label="日期"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
    </el-table>
  </section>
</template>

<script>
export default {
  name: 'EnterprisePartTimeList',
  data () {
    return {
      enterprisePartTimePostList: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }]
    };
  },
  methods: {
    tableRowClassName ({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    },
    toPartTimeAdd () {

    }
  }
};
</script>

<style lang="scss">
  .enterprise-part-time-post-list-container {
    padding: 56px 0 0 65px;
    .part-time-post-tooltip {
      margin-bottom: 20px;
      text-align: right;
    }
    .el-table .warning-row {
      background: oldlace;
    }
    .el-table .success-row {
      background: #f0f9eb;
    }
  }
</style>
