package com.libraries.fairy

import java.text.MessageFormat
import java.util.*
import java.util.Locale


class MessageResource(baseName: String) {

    private var baseName: String

    init {
        Objects.requireNonNull(baseName)
        this.baseName = baseName
    }

    companion object {
        val DEFAULT_BASE_NAME = "i18n.messages"

        fun of() {
            ofBaseName(DEFAULT_BASE_NAME)
        }

        fun ofBaseName(baseName: String): MessageResource {
            return MessageResource(baseName)
        }

    }

    fun getMessage(key: String): String? {
        return this.getMessage(key, Locale.getDefault())
    }

    fun getMessage(key: String, arguments: Array<Any?>): String? {
        return this.getMessage(key, Locale.getDefault(), arguments)
    }

    fun getMessage(key: String, locale: Locale): String? {
        return this.getMessage(key, locale, key)
    }

    fun getMessage(key: String, locale: Locale, arguments: Array<Any?>): String? {
        return this.getMessage(key, locale, key, arguments)
    }

    fun getMessage(key: String, defaultMessage: String): String? {
        return this.getMessage(key, Locale.getDefault(), defaultMessage)
    }

    fun getMessage(key: String, defaultMessage: String, arguments: Array<Any?>): String? {
        return this.getMessage(key, Locale.getDefault(), defaultMessage, arguments)
    }

    fun getMessage(key: String, locale: Locale, defaultMessage: String): String? {
        Objects.requireNonNull(key)
        Objects.requireNonNull(locale)
        Objects.requireNonNull(defaultMessage)
        val resourceBundle = getResourceBundle(locale)
        val message = getMessage(key, resourceBundle)
        return if (Objects.nonNull(message) && !message!!.isEmpty()) message else defaultMessage
    }

    fun getMessage(key: String, locale: Locale, defaultMessage: String, arguments: Array<Any?>): String? {
        Objects.requireNonNull(key)
        Objects.requireNonNull(locale)
        Objects.requireNonNull(defaultMessage)
        Objects.requireNonNull(arguments)
        val message = this.getMessage(key, locale, defaultMessage)
        return MessageFormat.format(message, *arguments)
    }

    private fun getResourceBundle(locale: Locale?): ResourceBundle? {
        return ResourceBundleFactory.getResourceBundle(locale!!, baseName)
    }

    private fun getMessage(key: String?, resourceBundle: ResourceBundle?): String? {
        return try {
            resourceBundle!!.getString(key)
        } catch (e: MissingResourceException) {
            null
        }
    }

    object Arguments {
        fun of(o1: Any): Array<Any> {
            return arrayOf(o1)
        }

        fun of(o1: Any, o2: Any): Array<Any> {
            return arrayOf(o1, o2)
        }

        fun of(o1: Any, o2: Any, o3: Any): Array<Any> {
            return arrayOf(o1, o2, o3)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any, o6: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5, o6)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any, o6: Any, o7: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5, o6, o7)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any, o6: Any, o7: Any, o8: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5, o6, o7, o8)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any, o6: Any, o7: Any, o8: Any, o9: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5, o6, o7, o8, o9)
        }

        fun of(o1: Any, o2: Any, o3: Any, o4: Any, o5: Any, o6: Any, o7: Any, o8: Any, o9: Any, o10: Any): Array<Any> {
            return arrayOf(o1, o2, o3, o4, o5, o6, o7, o8, o9, o10)
        }

        fun of(vararg values: Array<Any?>?): Array<out Array<Any?>?> {
            Objects.requireNonNull(values)
            return values
        }
    }

}