<template>
  <section class="enterprise-part-time-post-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>

export default {
  name: 'EnterprisePartTimePost'
};
</script>

<style lang="scss">

</style>
