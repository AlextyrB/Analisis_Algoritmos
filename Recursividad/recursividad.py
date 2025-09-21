def suma_recursiva(lista):

    if len(lista) == 0:
        return 0

    return lista[0] + suma_recursiva(lista[1:])


def contar_digitos(numero):
    if numero < 10:
        return 1

    return 1 + contar_digitos(numero // 10)

class Pila:

    def __init__(self):
        self.items = []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        if self.is_empty():
            raise IndexError("Pop de pila vacía")
        return self.items.pop()

    def peek(self):
        if self.is_empty():
            raise IndexError("Peek de pila vacía")
        return self.items[-1]

    def is_empty(self):
        return len(self.items) == 0

    def size(self):
        return len(self.items)

    def mostrar(self):
        return self.items.copy()


def eliminar_medio_pila(pila):
    if pila.is_empty():
        return

    tamaño = pila.size()
    posicion_medio = tamaño // 2

    pila_aux = Pila()

    for i in range(posicion_medio):
        pila_aux.push(pila.pop())

    if not pila.is_empty():
        pila.pop()

    while not pila_aux.is_empty():
        pila.push(pila_aux.pop())

def es_palindromo(cadena):

    cadena_limpia = ''.join(cadena.lower().split())

    def palindromo_recursivo(s):
        if len(s) <= 1:
            return True

        if s[0] != s[-1]:
            return False

        return palindromo_recursivo(s[1:-1])

    return palindromo_recursivo(cadena_limpia)


if __name__ == "__main__":
    print("=" * 60)
    print("EJEMPLOS DE USO")
    print("=" * 60)

    print("\n1. SUMA RECURSIVA:")
    lista1 = [1, 2, 3, 4, 5]
    lista2 = []
    lista3 = [10, -5, 3]

    print(f"suma_recursiva({lista1}) = {suma_recursiva(lista1)}")
    print(f"suma_recursiva({lista2}) = {suma_recursiva(lista2)}")
    print(f"suma_recursiva({lista3}) = {suma_recursiva(lista3)}")

    print("\n2. CONTAR DÍGITOS:")
    numeros = [123, 5, 9876, 1000]
    for num in numeros:
        print(f"contar_digitos({num}) = {contar_digitos(num)}")

    print("\n3. ELIMINAR ELEMENTO MEDIO DE PILA:")
    pila = Pila()
    elementos = [1, 2, 3, 4, 5, 6, 7]

    for elem in elementos:
        pila.push(elem)

    print(f"Pila original: {pila.mostrar()}")
    print(f"Tamaño: {pila.size()}")

    eliminar_medio_pila(pila)
    print(f"Pila después de eliminar medio: {pila.mostrar()}")
    print(f"Nuevo tamaño: {pila.size()}")

    # Prueba con pila de tamaño par
    pila2 = Pila()
    for elem in [1, 2, 3, 4]:
        pila2.push(elem)

    print(f"\nPila par original: {pila2.mostrar()}")
    eliminar_medio_pila(pila2)
    print(f"Pila par después de eliminar medio: {pila2.mostrar()}")


    print("\n4. VERIFICAR PALÍNDROMOS:")
    cadenas = ["radar", "python", "A man a plan a canal Panama",
               "race a car", "aabbaa", "", "a"]

    for cadena in cadenas:
        resultado = es_palindromo(cadena)
        print(f"es_palindromo('{cadena}') = {resultado}")