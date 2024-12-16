__author__      = "Felix Friesenbichler"
__email__       = "1127@htl.rennweg.at"
__license__     = "GPLv2"


def is_palindrom(s: str):
    """
    This method checks if the string s, which contains only one word, is a
    palindrome.

    >>> is_palindrom("racecar")
    True

    >>> is_palindrom("string")
    False
    """

    first_list = list(s)
    second_list = list(s)
    second_list.reverse()

    print(first_list)
    print(second_list)

    if first_list == second_list:
        return True
    else:
        return False


def is_palindrome_sentence(s: str):
    """
    Checks is the string s, which may contain a sentence, is a palindrome
    sentence.

    >>> is_palindrome_sentence("Was it a car or a cat I saw?")
    True

    >>> is_palindrome_sentence("Do Geese see God?")
    True

    >>> is_palindrome_sentence("A Toyota")
    True

    >>> is_palindrome_sentence("Warsaw was raw")
    True

    """

    char_list = (" ", ";", "?", ".", ",", ":", "!")
    string = s.lower()

    for char in char_list:
        string = string.replace(char, "")

    return is_palindrom(string)


def palindrome_product(x):
    """
    Finds the biggest palindrome number (smaller than x), which is the product
    of 3 digit number
    """





def get_dec_hex_palindrom(x):
    """
    Finds the biggest number (smaller than x), which is a palindrome in the
    decimal- as well as in the hexadecimal system.
    """


def to_base(number: int, base: int) -> str:
    """
    :param number: Zahl im 10er-Style
    :param base: Zielsystem (maximal 36)
    :return: Zahl im Zielsystem als String

    >>> to_base(1234,16)
    '4D2'
    """


if __name__ == "__main__":
    print("Hello World")
    var = "ss"
    print(len(var))
