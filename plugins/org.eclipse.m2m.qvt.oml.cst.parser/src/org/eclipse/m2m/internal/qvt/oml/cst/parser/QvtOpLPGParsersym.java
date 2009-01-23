/**
* <copyright>
*
* Copyright (c) 2005, 2008 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Elimination of some shift-reduce conflicts
*   E.D.Willink - Remove unnecessary warning suppression
*   E.D.Willink - 225493 Need ability to set CSTNode offsets
*
* </copyright>
*
* $Id: QvtOpLPGParsersym.java,v 1.51 2009/01/23 12:34:19 aigdalov Exp $
*/
/**
* <copyright>
*
* Copyright (c) 2006, 2007 Borland Inc.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Borland - Initial API and implementation
*
* </copyright>
*
* $Id: QvtOpLPGParsersym.java,v 1.51 2009/01/23 12:34:19 aigdalov Exp $
*/
/**
* <copyright>
*
* Copyright (c) 2006, 2007 Borland Inc.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Borland - Initial API and implementation
*
* </copyright>
*
* $Id: QvtOpLPGParsersym.java,v 1.51 2009/01/23 12:34:19 aigdalov Exp $
*/
/**
* <copyright>
*
* Copyright (c) 2006, 2007 Borland Inc.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Borland - Initial API and implementation
*
* </copyright>
*
* $Id: QvtOpLPGParsersym.java,v 1.51 2009/01/23 12:34:19 aigdalov Exp $
*/

package org.eclipse.m2m.internal.qvt.oml.cst.parser;

public interface QvtOpLPGParsersym {
    public final static int
      TK_NUMERIC_OPERATION = 70,
      TK_STRING_LITERAL = 42,
      TK_INTEGER_LITERAL = 54,
      TK_REAL_LITERAL = 55,
      TK_PLUS = 52,
      TK_MINUS = 53,
      TK_MULTIPLY = 20,
      TK_DIVIDE = 43,
      TK_GREATER = 22,
      TK_LESS = 23,
      TK_EQUAL = 5,
      TK_GREATER_EQUAL = 24,
      TK_LESS_EQUAL = 25,
      TK_NOT_EQUAL = 26,
      TK_LPAREN = 1,
      TK_RPAREN = 3,
      TK_LBRACE = 91,
      TK_RBRACE = 93,
      TK_LBRACKET = 97,
      TK_RBRACKET = 107,
      TK_ARROW = 119,
      TK_BAR = 96,
      TK_COMMA = 92,
      TK_COLON = 94,
      TK_COLONCOLON = 95,
      TK_SEMICOLON = 90,
      TK_DOT = 99,
      TK_DOTDOT = 120,
      TK_ATPRE = 110,
      TK_CARET = 121,
      TK_CARETCARET = 122,
      TK_QUESTIONMARK = 111,
      TK_QUOTE_STRING_LITERAL = 112,
      TK_ADD_ASSIGN = 138,
      TK_RESET_ASSIGN = 100,
      TK_AT_SIGN = 148,
      TK_EXCLAMATION_MARK = 113,
      TK_NOT_EQUAL_EXEQ = 108,
      TK_INTEGER_RANGE_START = 123,
      TK_break = 172,
      TK_class = 149,
      TK_composes = 150,
      TK_constructor = 173,
      TK_continue = 174,
      TK_datatype = 175,
      TK_default = 176,
      TK_derived = 151,
      TK_do = 177,
      TK_elif = 178,
      TK_enum = 179,
      TK_except = 180,
      TK_exception = 181,
      TK_from = 182,
      TK_literal = 183,
      TK_ordered = 152,
      TK_primitive = 184,
      TK_raise = 185,
      TK_readonly = 153,
      TK_references = 154,
      TK_tag = 103,
      TK_try = 186,
      TK_typedef = 187,
      TK_unlimited = 188,
      TK_invalid = 189,
      TK_COLONCOLONEQUAL = 139,
      TK_STEREOTYPE_QUALIFIER_OPEN = 140,
      TK_STEREOTYPE_QUALIFIER_CLOSE = 155,
      TK_MULTIPLICITY_RANGE = 156,
      TK_TILDE_SIGN = 157,
      TK_self = 44,
      TK_inv = 190,
      TK_pre = 191,
      TK_post = 192,
      TK_context = 193,
      TK_package = 194,
      TK_endpackage = 195,
      TK_def = 196,
      TK_if = 71,
      TK_then = 141,
      TK_else = 124,
      TK_endif = 125,
      TK_and = 46,
      TK_or = 47,
      TK_xor = 48,
      TK_not = 56,
      TK_implies = 142,
      TK_let = 77,
      TK_in = 104,
      TK_true = 57,
      TK_false = 58,
      TK_body = 27,
      TK_derive = 28,
      TK_init = 158,
      TK_null = 49,
      TK_attr = 197,
      TK_oper = 198,
      TK_Set = 6,
      TK_Bag = 7,
      TK_Sequence = 8,
      TK_Collection = 9,
      TK_OrderedSet = 10,
      TK_iterate = 29,
      TK_forAll = 30,
      TK_exists = 31,
      TK_isUnique = 32,
      TK_any = 33,
      TK_one = 34,
      TK_collect = 35,
      TK_select = 36,
      TK_reject = 37,
      TK_collectNested = 38,
      TK_sortedBy = 39,
      TK_closure = 40,
      TK_oclIsKindOf = 59,
      TK_oclIsTypeOf = 60,
      TK_oclAsType = 61,
      TK_oclIsNew = 62,
      TK_oclIsUndefined = 63,
      TK_oclIsInvalid = 64,
      TK_oclIsInState = 65,
      TK_allInstances = 41,
      TK_String = 11,
      TK_Integer = 12,
      TK_UnlimitedNatural = 13,
      TK_Real = 14,
      TK_Boolean = 15,
      TK_Tuple = 21,
      TK_OclAny = 16,
      TK_OclVoid = 17,
      TK_Invalid = 18,
      TK_OclMessage = 19,
      TK_OclInvalid = 72,
      TK_end = 105,
      TK_while = 73,
      TK_out = 126,
      TK_object = 74,
      TK_transformation = 127,
      TK_import = 143,
      TK_library = 114,
      TK_metamodel = 199,
      TK_mapping = 128,
      TK_query = 129,
      TK_helper = 130,
      TK_inout = 131,
      TK_when = 115,
      TK_var = 79,
      TK_configuration = 116,
      TK_intermediate = 117,
      TK_property = 106,
      TK_opposites = 159,
      TK_population = 144,
      TK_map = 66,
      TK_new = 75,
      TK_xmap = 67,
      TK_late = 68,
      TK_log = 78,
      TK_assert = 80,
      TK_with = 160,
      TK_resolve = 82,
      TK_resolveone = 83,
      TK_resolveIn = 84,
      TK_resolveoneIn = 85,
      TK_invresolve = 86,
      TK_invresolveone = 87,
      TK_invresolveIn = 88,
      TK_invresolveoneIn = 89,
      TK_modeltype = 145,
      TK_uses = 161,
      TK_where = 162,
      TK_refines = 163,
      TK_access = 101,
      TK_extends = 98,
      TK_blackbox = 132,
      TK_abstract = 133,
      TK_static = 118,
      TK_result = 45,
      TK_main = 102,
      TK_this = 50,
      TK_switch = 69,
      TK_case = 146,
      TK_xselect = 164,
      TK_xcollect = 165,
      TK_selectOne = 166,
      TK_collectOne = 167,
      TK_collectselect = 168,
      TK_collectselectOne = 169,
      TK_return = 81,
      TK_rename = 147,
      TK_disjuncts = 134,
      TK_merges = 135,
      TK_inherits = 136,
      TK_forEach = 170,
      TK_forOne = 171,
      TK_compute = 76,
      TK_Dict = 51,
      TK_List = 109,
      TK_EOF_TOKEN = 137,
      TK_IDENTIFIER = 2,
      TK_ERROR_TOKEN = 4;

      public final static String orderedTerminalSymbols[] = {
                 "",
                 "LPAREN",
                 "IDENTIFIER",
                 "RPAREN",
                 "ERROR_TOKEN",
                 "EQUAL",
                 "Set",
                 "Bag",
                 "Sequence",
                 "Collection",
                 "OrderedSet",
                 "String",
                 "Integer",
                 "UnlimitedNatural",
                 "Real",
                 "Boolean",
                 "OclAny",
                 "OclVoid",
                 "Invalid",
                 "OclMessage",
                 "MULTIPLY",
                 "Tuple",
                 "GREATER",
                 "LESS",
                 "GREATER_EQUAL",
                 "LESS_EQUAL",
                 "NOT_EQUAL",
                 "body",
                 "derive",
                 "iterate",
                 "forAll",
                 "exists",
                 "isUnique",
                 "any",
                 "one",
                 "collect",
                 "select",
                 "reject",
                 "collectNested",
                 "sortedBy",
                 "closure",
                 "allInstances",
                 "STRING_LITERAL",
                 "DIVIDE",
                 "self",
                 "result",
                 "and",
                 "or",
                 "xor",
                 "null",
                 "this",
                 "Dict",
                 "PLUS",
                 "MINUS",
                 "INTEGER_LITERAL",
                 "REAL_LITERAL",
                 "not",
                 "true",
                 "false",
                 "oclIsKindOf",
                 "oclIsTypeOf",
                 "oclAsType",
                 "oclIsNew",
                 "oclIsUndefined",
                 "oclIsInvalid",
                 "oclIsInState",
                 "map",
                 "xmap",
                 "late",
                 "switch",
                 "NUMERIC_OPERATION",
                 "if",
                 "OclInvalid",
                 "while",
                 "object",
                 "new",
                 "compute",
                 "let",
                 "log",
                 "var",
                 "assert",
                 "return",
                 "resolve",
                 "resolveone",
                 "resolveIn",
                 "resolveoneIn",
                 "invresolve",
                 "invresolveone",
                 "invresolveIn",
                 "invresolveoneIn",
                 "SEMICOLON",
                 "LBRACE",
                 "COMMA",
                 "RBRACE",
                 "COLON",
                 "COLONCOLON",
                 "BAR",
                 "LBRACKET",
                 "extends",
                 "DOT",
                 "RESET_ASSIGN",
                 "access",
                 "main",
                 "tag",
                 "in",
                 "end",
                 "property",
                 "RBRACKET",
                 "NOT_EQUAL_EXEQ",
                 "List",
                 "ATPRE",
                 "QUESTIONMARK",
                 "QUOTE_STRING_LITERAL",
                 "EXCLAMATION_MARK",
                 "library",
                 "when",
                 "configuration",
                 "intermediate",
                 "static",
                 "ARROW",
                 "DOTDOT",
                 "CARET",
                 "CARETCARET",
                 "INTEGER_RANGE_START",
                 "else",
                 "endif",
                 "out",
                 "transformation",
                 "mapping",
                 "query",
                 "helper",
                 "inout",
                 "blackbox",
                 "abstract",
                 "disjuncts",
                 "merges",
                 "inherits",
                 "EOF_TOKEN",
                 "ADD_ASSIGN",
                 "COLONCOLONEQUAL",
                 "STEREOTYPE_QUALIFIER_OPEN",
                 "then",
                 "implies",
                 "import",
                 "population",
                 "modeltype",
                 "case",
                 "rename",
                 "AT_SIGN",
                 "class",
                 "composes",
                 "derived",
                 "ordered",
                 "readonly",
                 "references",
                 "STEREOTYPE_QUALIFIER_CLOSE",
                 "MULTIPLICITY_RANGE",
                 "TILDE_SIGN",
                 "init",
                 "opposites",
                 "with",
                 "uses",
                 "where",
                 "refines",
                 "xselect",
                 "xcollect",
                 "selectOne",
                 "collectOne",
                 "collectselect",
                 "collectselectOne",
                 "forEach",
                 "forOne",
                 "break",
                 "constructor",
                 "continue",
                 "datatype",
                 "default",
                 "do",
                 "elif",
                 "enum",
                 "except",
                 "exception",
                 "from",
                 "literal",
                 "primitive",
                 "raise",
                 "try",
                 "typedef",
                 "unlimited",
                 "invalid",
                 "inv",
                 "pre",
                 "post",
                 "context",
                 "package",
                 "endpackage",
                 "def",
                 "attr",
                 "oper",
                 "metamodel"
             };

    public final static boolean isValidForParser = true;
}
