package com.libraries.fairy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.util.*

class ResourceBundleFactoryTest {

    @Test
    fun resourceBundle_shouldReturnNewFromCache() {
        val resourceBundleOne = ResourceBundleFactory.getResourceBundle(Locale.US, "i18n.messages")
        assertNotNull(resourceBundleOne)
        val resourceBundleTwo = ResourceBundleFactory.getResourceBundle(Locale.US, "i18n.messages")
        assertNotNull(resourceBundleTwo)
        assertEquals(resourceBundleOne, resourceBundleTwo)
    }
}