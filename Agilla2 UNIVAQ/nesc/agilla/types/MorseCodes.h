#ifndef MORSECODES
#define MORSECODES


#define MAX_CODE_LENGTH 7

typedef nx_struct _AgillaMorseCode
{
	nx_uint8_t ascii; // ascii value of the symbol. eg. 'b'
	nx_uint8_t size; // number of bits used (from the MSB, 0..8). eg. 3
	nx_uint8_t code[ MAX_CODE_LENGTH ];
} AgillaMorseCode;

// timing
#define DOT_MSEC 100
#define DASH_MSEC 3*DOT_MSEC

// letter and word separator delay
#define WORD_SEPARATOR 5*DOT_MSEC
#define LETTER_SEPARATOR DASH_MSEC

#define NUMOFMORSECODES 49
//AgillaMorseCode MorseCodes[ NUMOFMORSECODES ] = 
//{
//	{ 'a', 2, 0x40 }, { 'b', 4, 0x80 }, { 'c', 4, 0xA0 },
//	{ 'd', 3, 0x80 }, { 'e', 1, 0x00 }, { 'f', 4, 0x20 },
//	{ 'g', 3, 0xc0 }, { 'h', 4, 0x00 }, { 'i', 2, 0x00 },
//	{ 'j', 4, 0x70 }, { 'k', 3, 0xa0 }, { 'l', 4, 0x40 },
//	{ 'm', 2, 0xc0 }, { 'n', 2, 0x80 }, { 'o', 3, 0xE0 },
//	{ 'p', 4, 0x60 }, { 'q', 4, 0xd0 }, { 'r', 3, 0x40 },
//	{ 's', 3, 0x00 }, { 't', 1, 0x80 }, { 'u', 3, 0x20 },
//	{ 'v', 4, 0x10 }, { 'w', 3, 0x60 }, { 'x', 4, 0x90 },
//	{ 'y', 4, 0xb0 }, { 'z', 4, 0xc0 }, { '0', 5, 0xF8 },
//	{ '1', 5, 0x78 }, { '2', 5, 0x38 }, { '3', 5, 0x18 },
//	{ '4', 5, 0x08 }, { '5', 5, 0x00 }, { '6', 5, 0x10 },
//	{ '7', 5, 0x30 }, { '8', 5, 0x70 }, { '9', 5, 0xF0 },
//	{ '*', 6, 0x54 }, { ',', 6, 0xCC }, { ':', 6, 0xE0 },
//	{ '?', 6, 0x30 }, { '=', 5, 0x88 }, { '-', 6, 0x84 },
//	{ '(', 5, 0xB0 }, { ')', 6, 0xB4 }, { '"', 6, 0x48 },
//	{ '\'', 6, 0x78 }, { '/', 5, 0x90 }, { '_', 6, 0x34 },
//	{ '@', 6, 0x68 }
//};

AgillaMorseCode MorseCodes[ NUMOFMORSECODES ] = 
{
	{ 'a', 2, {'.','-', 0 , 0 , 0 , 0 , 0 } },
	{ 'b', 4, {'-','.','.','.', 0 , 0 , 0 } },
	{ 'c', 4, {'-','.','-','.', 0 , 0 , 0 } },
	{ 'd', 3, {'-','.','.', 0 , 0 , 0 , 0 } },
	{ 'e', 1, {'.', 0 , 0 , 0 , 0 , 0 , 0 } },
	{ 'f', 4, {'.','.','-','.', 0 , 0 , 0 } },
	{ 'g', 3, {'-','-','.', 0 , 0 , 0 , 0 } },
	{ 'h', 4, {'.','.','.','.', 0 , 0 , 0 } },
	{ 'i', 2, {'.','.', 0 , 0 , 0 , 0 , 0 } },
	{ 'j', 4, {'.','-','-','-', 0 , 0 , 0 } },
	{ 'k', 3, {'-','.','-', 0 , 0 , 0 , 0 } },
	{ 'l', 4, {'.','-','.','.', 0 , 0 , 0 } },
	{ 'm', 2, {'-','-', 0 , 0 , 0 , 0 , 0 } },
	{ 'n', 2, {'-','.', 0 , 0 , 0 , 0 , 0 } },
	{ 'o', 3, {'-','-','-', 0 , 0 , 0 , 0 } },
	{ 'p', 4, {'.','-','-','.', 0 , 0 , 0 } },
	{ 'q', 4, {'-','-','.','-', 0 , 0 , 0 } },
	{ 'r', 3, {'.','-','.', 0 , 0 , 0 , 0 } },
	{ 's', 3, {'.','.','.', 0 , 0 , 0 , 0 } },
	{ 't', 1, {'-', 0 , 0 , 0 , 0 , 0 , 0 } },
	{ 'u', 3, {'.','.','-', 0 , 0 , 0 , 0 } },
	{ 'v', 4, {'.','.','.','-', 0 , 0 , 0 } },
	{ 'w', 3, {'.','-','-', 0 , 0 , 0 , 0 } },
	{ 'x', 4, {'-','.','.','-', 0 , 0 , 0 } },
	{ 'y', 4, {'-','.','-','-', 0 , 0 , 0 } },
	{ 'z', 4, {'-','-','.','.', 0 , 0 , 0 } },
	{ '0', 5, {'-','-','-','-','-', 0 , 0 } },
	{ '1', 5, {'.','-','-','-','-', 0 , 0 } },
	{ '2', 5, {'.','.','-','-','-', 0 , 0 } },
	{ '3', 5, {'.','.','.','-','-', 0 , 0 } },
	{ '4', 5, {'.','.','.','.','-', 0 , 0 } },
	{ '5', 5, {'.','.','.','.','.', 0 , 0 } },
	{ '6', 5, {'.','.','.','-','.', 0 , 0 } },
	{ '7', 5, {'.','.','-','-','.', 0 , 0 } },
	{ '8', 5, {'.','-','-','-','.', 0 , 0 } },
	{ '9', 5, {'-','-','-','-','.', 0 , 0 } },
	{ '*', 6, {'.','-','.','-','.','-', 0 } },
	{ ',', 6, {'-','-','.','.','-','-', 0 } },
	{ ':', 6, {'-','-','-','.','.','.', 0 } },
	{ '?', 6, {'.','.','-','-','.','.', 0 } },
	{ '=', 5, {'-','.','.','.','-', 0 , 0 } },
	{ '-', 6, {'-','.','.','.','.','-', 0 } },
	{ '(', 5, {'-','.','-','-','.', 0 , 0 } },
	{ ')', 6, {'-','.','-','-','-','.', 0 } },
	{ '}', 6, {'.','-','.','.','-','.', 0 } },
   { '\'', 6, {'.','-','-','-','-','.', 0 } },
	{ '/', 5, {'-','.','.','-','-', 0 , 0 } },
	{ '_', 6, {'.','.','-','-','.','-', 0 } },
	{ '@', 6, {'.','-','-','.','-','.', 0 } }
};
#endif