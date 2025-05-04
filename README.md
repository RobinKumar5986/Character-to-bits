# Character to Bits

**Character** to Bits is a Java 18 project designed to convert characters into binary bit patterns suitable for display on digital boards. The project generates header files ```(.h)``` containing bit arrays representing characters in either **8x8** or **16x16** pixel grids. These arrays can be used to render characters on digital displays, such as LED matrices or other pixel-based boards.

--

## 📌 Features

- Converts Unicode characters to binary representations
- Supports both 8x8 and 16x16 output formats
- Outputs character data in `.h` header files
- Useful for embedded or low-level display systems
- Simple to extend with more characters or resolutions

## 🔍 How It Works

This project provides a simple and extensible way to convert **any character from any language** into a **bit-level representation**, suitable for rendering on digital displays like LED matrices.

### 🧱 Step-by-Step Process

1. **Character List Definition**  
   You start by providing a list of characters in any language (e.g., English, Hindi, Bengali, Tamil, etc.). These characters are stored and iterated through programmatically.

2. **Character to Image Conversion**  
   Each character is rendered onto an image (usually a monochrome buffered image) using a suitable font and size.

3. **Pixel to Bit Conversion**  
   The image is scanned pixel by pixel. For each pixel:
   - The **grayscale value** (intensity) is calculated.
   - A **threshold** is applied (usually average or fixed).
   - Pixels are then converted to **binary** (0 for black/dark, 1 for white/light).

4. **Formatted Output**  
   - The resulting bits are grouped into bytes or words (depending on 8x8 or 16x16).
   - These values are formatted into `.h` files as **hexadecimal arrays**, ready for integration with display systems.

5. **Generated Files**  
   For each character, two header files are generated:
    - For 8x8 grids: [CharacterSetName]_8x8.h
    - For 16x16 grids: [CharacterSetName]_16x16.h
    The file generation logic is implemented as follows:
```java
fileGen(8, 8, language, CharactersContainers.CHARACTER_ARRAY_NAMES[ind] + "_" + 8 + "x" + 8 + ".h");
fileGen(16, 16, language, CharactersContainers.CHARACTER_ARRAY_NAMES[ind] + "_" + 16 + "x" + 16 + ".h");
 ```

## Example: Character "অ" (Bengali Letter A)

Below are examples of how the character "অ" is represented in 8x8 and 16x16 bit patterns.

**8x8 Representation**

The 8x8 bit pattern for "অ" is stored in a file named ```অ_8x8.h```. The data is an array of 8 bytes, each representing one row of the 8x8 grid.

File Content Example:

```
0x00, 0x00, 0x10, 0xA8, 0x48, 0x7C, 0x06, 0x00, // অ
```

**16x16 Representation**

The 16x16 bit pattern for "অ" is stored in a file named ```অ_16x16.h```. The data is an array of 16 16-bit words, each representing one row of the 16x16 grid.

File Content Example:

```
0x0000, 0x0000, 0x0000, 0x0000, 0x7FFF, 0x000C, 0x63CC, 0x226C, 0x226C, 0x326C, 0x186C, 0x0FFC, 0x001C, 0x000C, 0x0000, 0x0000, // অ
```
## Overview
<div align="center">
   
<div align="center">
   <img src="https://github.com/user-attachments/assets/3f012a48-5a0a-42b1-98a7-08a60d023130" alt="Overview Image 1" width="300" style="margin: 10px;" />
<div align="center">
   
<img src="https://github.com/user-attachments/assets/641de6c9-3f31-47e5-bdc8-8f126504449d" alt="Overview Image 2" width="700" style="margin: 10px;" />

<img src="https://github.com/user-attachments/assets/c0d9c89a-83cd-4a5b-98b3-7ead0e842b6f" alt="Overview Image 3" width="700" style="margin: 10px;" />

</div>

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

You are free to:

- ✅ Use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the software.

Under the following conditions:

- 📌 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
- ⚠️ The software is provided "as is", without warranty of any kind, express or implied.

### 📝 MIT License (Full Text)
MIT License

Copyright (c) 2025 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.

--





