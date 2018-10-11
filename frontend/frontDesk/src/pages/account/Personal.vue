<template>
  <section class="personal-container">
    <personal-nav/>
    <transition name="slide" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import { mapState } from 'vuex';
import PersonalNav from '@/components/account/PersonalNav';

export default {
  name: 'Persona',
  components: {
    PersonalNav
  },
  computed: {
    ...mapState('account', [
      'accountInfo'
    ])
  },
  watch: {
    accountInfo: {
      handler () {
        // 验证是否登陆
        this.checkSignIn();
      }
    }
  },
  methods: {
    checkSignIn () {
      if (!this.accountInfo.id) {
        // 未登录跳转登陆页面
        this.$router.push('/account/signIn');
      }
    }
  }
};
</script>

<style lang="scss">
  .personal-container {
    display: flex;
    & > section:last-child {
      flex: 1;
    }
  }
</style>
