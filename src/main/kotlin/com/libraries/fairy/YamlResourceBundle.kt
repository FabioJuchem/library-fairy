package com.libraries.fairy

import org.yaml.snakeyaml.Yaml
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream


class YamlResourceBundle(stream: InputStream) : ResourceBundle() {

    val EMPTY_KEY = ""

    private val lookup: MutableMap<String, String> = mutableMapOf()

    init  {
        Objects.requireNonNull(stream)
        val metadata: Map<*, *>? = Yaml().loadAs(stream, Map::class.java)
        loadLookup(EMPTY_KEY, metadata as Map<String, Any>)
    }

    fun loadLookup(currentKey: String, map: Map<String, Any>) {
        map.forEach { key, value ->
            val newKey = if (currentKey.isEmpty()) key else java.lang.String.format("%s.%s", currentKey, key)
            if (value is Map<*, *>) {
                loadLookup(newKey, value as Map<String, Any>)
            } else {
                lookup.put(newKey, value.toString())
            }
        }
    }

    /** {@inheritDoc}  */
    override fun handleKeySet(): Set<String?>? {
        return lookup.keys
    }

    /** {@inheritDoc}  */
    override fun getKeys(): Enumeration<String?>? {
        return Collections.enumeration(lookup.keys as Collection<String?>)
    }

    /** {@inheritDoc}  */
    override fun handleGetObject(key: String?): Any? {
        Objects.requireNonNull(key)
        return lookup[key]
    }

    class Control : ResourceBundle.Control() {
        companion object {
            private val FORMAT_DEFAULT = Arrays.asList("yml", "yaml")

            /**
             * Singleton instance.
             */
            val INSTANCE: Control = Control()
        }

        override fun getFormats(baseName: String?): List<String?>? {
            Objects.requireNonNull(baseName)
            val values: List<String> = Stream.concat(super.getFormats(baseName).stream(), FORMAT_DEFAULT.stream()).collect(Collectors.toList())
            return Collections.unmodifiableList(values)
        }

        /** {@inheritDoc}  */
        @Throws(IllegalAccessException::class, InstantiationException::class, IOException::class)
        override fun newBundle(baseName: String?, locale: Locale?, format: String?, loader: ClassLoader, reload: Boolean): ResourceBundle? {
            if (FORMAT_DEFAULT.contains(format)) {
                val bundleName = super.toBundleName(baseName, locale)
                val resourceName = super.toResourceName(bundleName, format)
                val stream = loader.getResourceAsStream(resourceName)
                return if (Objects.nonNull(stream)) buildBundle(stream) else null
            }
            return super.newBundle(baseName, locale, format, loader, reload)
        }

        private fun buildBundle(stream: InputStream): YamlResourceBundle? {
            return try {
                val bundle = YamlResourceBundle(stream)
                stream.close()
                bundle
            } catch (e: Exception) {
                null
            }
        }
    }

}