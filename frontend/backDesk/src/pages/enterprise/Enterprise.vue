<template>
  <section class="enterprise-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'Enterprise'
};
</script>

<style lang="scss">

</style>
