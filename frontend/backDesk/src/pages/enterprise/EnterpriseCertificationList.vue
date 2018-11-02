<template>
  <section class="list-container">
    <router-link to="/enterprise/certification/add">
      <el-button type="danger">添加</el-button>
    </router-link>
    <el-table
      :data="enterpriseCertificationList"
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
  name: 'List',
  data() {
    return {
      enterpriseCertificationList: [{
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

</style>
