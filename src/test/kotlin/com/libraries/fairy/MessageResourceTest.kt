package com.libraries.fairy
import com.libraries.fairy.MessageResource.Arguments.of
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*


internal class MessageResourceTest {
    private val messageResource = MessageResource.ofBaseName(MessageResource.DEFAULT_BASE_NAME)

    companion object {
        private val LOCALE_PT_BR= Locale("pt", "BR")
        private val ARGUMENTS = arrayOf<Any?>("an argument")
        private  val DEFAULT_MESSAGE: String = "default message"
        private val LOCALE_NULL = null
        private val ARGUMENTS_NULL: Array<Any?>? = null
        private val DEFAULT_MESSAGE_NULL = null
    }

    @Test
    fun of_default() {
        Assertions.assertNotNull(MessageResource.of())
    }

    @Test
    fun of_baseName() {
        Assertions.assertNotNull(MessageResource.ofBaseName(MessageResource.DEFAULT_BASE_NAME))
    }

    @Test
    fun message_key_shouldReturnMessage() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("one", messageResource.getMessage("message.one"))
    }

    @Test
    fun message_key_shouldReturnKeyWhenNotFoundInMessagesFile() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("message.not.found.in.file", messageResource.getMessage("message.not.found.in.file"))
    }

    @Test
    fun message_key_arguments_shouldReturnMessage() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("message with argument - an argument", messageResource.getMessage("message.argument", ARGUMENTS))
    }

    @Test
    fun message_key_arguments_shouldReturnKeyWhenNotFoundInMessagesFile() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("message.not.found.in.file", messageResource.getMessage("message.not.found.in.file", ARGUMENTS))
    }

    @Test
    fun message_key_locale_shouldReturnMessage() {
        Assertions.assertEquals("um", messageResource.getMessage("message.one", LOCALE_PT_BR))
    }

    @Test
    fun message_key_locale_shouldReturnKey_whenNotFoundInMessagesFile() {
        Assertions.assertEquals("message.not.found.in.file", messageResource.getMessage("message.not.found.in.file", LOCALE_PT_BR))
    }

    @Test
    fun message_key_locale_arguments_shouldReturnMessage() {
        Assertions.assertEquals("mensagem com argumento - an argument", messageResource.getMessage("message.argument", LOCALE_PT_BR, ARGUMENTS))
    }

    @Test
    fun message_key_locale_arguments_shouldReturnKey_whenNotFoundInMessagesFile() {
        Assertions.assertEquals("message.not.found.in.file", messageResource.getMessage("message.not.found.in.file", LOCALE_PT_BR, ARGUMENTS))
    }

    @Test
    fun message_key_defaultMessage_shouldReturnMessage() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("one", messageResource.getMessage("message.one", DEFAULT_MESSAGE))
    }

    @Test
    fun message_key_defaultMessage_shouldReturnDefaultMessage_whenNotFoundInMessagesFile() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals(DEFAULT_MESSAGE, messageResource.getMessage("message.not.found.in.file", DEFAULT_MESSAGE))
    }

    @Test
    fun message_key_defaultMessage_arguments_shouldReturnMessage() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals("one", messageResource.getMessage("message.one", DEFAULT_MESSAGE, ARGUMENTS))
    }

    @Test
    fun message_key_defaultMessage_arguments_shouldReturnDefaultMessage_whenNotFoundInMessagesFile() {
        Locale.setDefault(Locale.US)
        Assertions.assertEquals(DEFAULT_MESSAGE, messageResource.getMessage("message.not.found.in.file", DEFAULT_MESSAGE, ARGUMENTS))
    }


    @Test
    fun message_key_locale_defaultMessage_arguments_shouldReturnMessage() {
        Assertions.assertEquals("mensagem com argumento - an argument", messageResource.getMessage("message.argument", LOCALE_PT_BR, DEFAULT_MESSAGE, ARGUMENTS))
    }

    @Test
    fun message_key_locale_defaultMessage_arguments_shouldReturDefaultMessage_whenNotFoundInMessagesFile() {
        Assertions.assertEquals(DEFAULT_MESSAGE, messageResource.getMessage("message.not.found.in.file", LOCALE_PT_BR, DEFAULT_MESSAGE, ARGUMENTS))
    }

    @Test
    fun arguments_of_1() {
        Assertions.assertArrayEquals(arrayOf("1"), of("1"))
    }

    @Test
    fun arguments_of_2() {
        Assertions.assertArrayEquals(arrayOf("1", "2"), of("1", "2"))
    }

    @Test
    fun arguments_of_3() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3"), of("1", "2", "3"))
    }

    @Test
    fun arguments_of_4() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4"), of("1", "2", "3", "4"))
    }

    @Test
    fun arguments_of_5() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5"), of("1", "2", "3", "4", "5"))
    }

    @Test
    fun arguments_of_6() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5", "6"), of("1", "2", "3", "4", "5", "6"))
    }

    @Test
    fun arguments_of_7() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5", "6", "7"), of("1", "2", "3", "4", "5", "6", "7"))
    }

    @Test
    fun arguments_of_8() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5", "6", "7", "8"), of("1", "2", "3", "4", "5", "6", "7", "8"))
    }

    @Test
    fun arguments_of_9() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9"), of("1", "2", "3", "4", "5", "6", "7", "8", "9"))
    }

    @Test
    fun arguments_of_10() {
        Assertions.assertArrayEquals(arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"), of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"))
    }

    @Test
    fun arguments_of_varargs() {
        Assertions.assertArrayEquals(arrayOf("one", "two"), of("one", "two"))
    }
}
