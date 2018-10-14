<template>
  <section class="enterprise-certification-container">
    <transition name="slide-bottom" mode="out-in">
      <router-view/>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'EnterpriseCertification'
};
</script>

<style lang="scss">

</style>
