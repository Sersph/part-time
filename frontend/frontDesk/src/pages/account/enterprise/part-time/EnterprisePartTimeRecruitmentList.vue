<template>
  <section class="enterprise-part-time-recruitment-list-container">
    <el-table
      :data="enterprisePartTimeRecruitmentList"
      style="width: 100%"
      :row-class-name="tableRowClassName">
      <el-table-column
        prop="date"
        label="日期1"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名2"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址3">
      </el-table-column>
    </el-table>
  </section>
</template>

<script>
export default {
  name: 'EnterprisePartTimeRecruitment',
  data() {
    return {
      enterprisePartTimeRecruitmentList: [{
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
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return 'warning-row';
      } else if (rowIndex === 3) {
        return 'success-row';
      }
      return '';
    }
  }
};
</script>

<style lang="scss">
  .enterprise-part-time-recruitment-container {
    .el-table .warning-row {
      background: oldlace;
    }

    .el-table .success-row {
      background: #f0f9eb;
    }
  }
</style>
