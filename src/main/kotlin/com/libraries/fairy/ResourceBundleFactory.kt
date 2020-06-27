package com.libraries.fairy

import java.util.*


class ResourceBundleFactory {

    companion object {

        private val CACHE: MutableMap<Locale, ResourceBundle?> = HashMap()

        private fun ResourceBundleFactory() {}

        @Synchronized
        fun getResourceBundle(locale: Locale, baseName: String): ResourceBundle? {
            if (isThereInCache(locale)) {
                return CACHE[locale]
            }
            val bundle = buildBundle(locale, baseName)
            addToCache(locale, bundle)
            return bundle
        }

        private fun isThereInCache(locale: Locale): Boolean {
            return CACHE.containsKey(locale)
        }

        private fun buildBundle(locale: Locale, baseName: String): ResourceBundle {
            return ResourceBundle.getBundle(baseName, locale, YamlResourceBundle.Control.INSTANCE)
        }

        private fun addToCache(locale: Locale, bundle: ResourceBundle): ResourceBundle? {
            return CACHE.put(locale, bundle)
        }

    }



}