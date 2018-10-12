<template>
  <section class="enterprise-container">
    <enterprise-nav/>
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
import EnterpriseNav from '@/components/account/enterprise/EnterpriseNav';

export default {
  name: 'Enterprise',
  components: {
    EnterpriseNav
  }
};
</script>

<style lang="scss">
  .enterprise-container {
    display: flex;
    & > section:last-child {
      flex: 1;
      padding: 30px;
    }
  }
</style>
