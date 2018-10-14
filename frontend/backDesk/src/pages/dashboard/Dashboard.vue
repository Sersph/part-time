<template>
  <section class="dashboard-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'Dashboard'
};
</script>

<style lang="scss">

</style>
