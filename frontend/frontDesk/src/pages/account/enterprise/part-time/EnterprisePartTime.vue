<template>
  <section class="enterprise-part-time-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'EnterprisePartTime'
};
</script>

<style lang="scss">

</style>
