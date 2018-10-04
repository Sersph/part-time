<template>
  <section class="select-location-container">
    <el-dialog title="切换城市" :visible.sync="selectLocationContainerVisibleFlag" type="text">
      <el-form :model="form">
        <el-form-item>
          <el-row>
            <el-col :span="8">
              <el-select class="province" v-model="form.region" placeholder="省份">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-col>
            <el-col :span="8">
              <el-select class="city" v-model="form.region" placeholder="城市">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-col>
            <el-col  :span="8">
              <el-select class="area" v-model="form.region" placeholder="地区">
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="selectLocationContainerVisibleFlag = false">取 消</el-button>
        <el-button type="primary" @click="changeCurrentLocation">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
export default {
  name: 'SelectLocation',
  data () {
    return {
      selectLocationContainerVisibleFlag: false,
      form: {}
    };
  },
  methods: {
    changeCurrentLocation () {
      console.log(1);
      this.selectLocationContainerVisibleFlag = false;
    }
  }
};
</script>

<style lang="scss">
  .select-location-container {
    .el-select {
      display: block;
      padding-left: 10px;
      padding-right: 10px;
    }
    .el-select.province:first-child {
      padding-left: 0;
    }
    .el-select.area:last-child {
      padding-right: 0;
    }
  }
</style>
