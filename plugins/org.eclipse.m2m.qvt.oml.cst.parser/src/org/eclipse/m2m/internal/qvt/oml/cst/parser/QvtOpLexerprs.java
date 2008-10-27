/**
* <copyright>
*
* Copyright (c) 2005, 2007 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   IBM - Initial API and implementation
*   E.D.Willink - Lexer and Parser refactoring to support extensibility and flexible error handling
*
* </copyright>
*
* $Id: QvtOpLexerprs.java,v 1.16 2008/10/27 14:26:31 aigdalov Exp $
*/
/**
* <copyright>
*
* Copyright (c) 2006-2008 Borland Inc.
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
* $Id: QvtOpLexerprs.java,v 1.16 2008/10/27 14:26:31 aigdalov Exp $
*/

package org.eclipse.m2m.internal.qvt.oml.cst.parser;

public class QvtOpLexerprs implements lpg.lpgjavaruntime.ParseTable, QvtOpLexersym {

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            1,1,3,3,3,1,1,1,1,1,
            5,1,1,1,1,1,1,1,1,1,
            1,2,2,2,1,1,1,1,2,1,
            1,1,2,1,1,2,4,1,2,1,
            1,1,2,2,3,2,2,0,1,2,
            2,2,1,2,1,2,3,2,3,3,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,2,3,1,1,1,1,
            1,2,1,2,2,2,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,2,2,1,2,
            1,2,0,1,3,3,3,3,3,3,
            3,4,4,3,3,1,2,2,1,1,
            2,1,1,1
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,20,20,20,20,20,20,20,20,20,
            20,21,22,22,22,26,26,26,26,27,
            27,25,25,6,6,35,35,29,13,13,
            13,9,9,9,9,9,2,2,2,2,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,4,4,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,4,4,4,4,4,4,4,4,
            4,4,1,1,1,1,1,1,1,1,
            1,1,37,37,37,37,37,37,37,37,
            38,38,38,38,38,38,38,38,38,38,
            38,38,36,36,39,39,39,15,15,40,
            40,28,28,11,11,11,11,31,31,31,
            31,31,31,31,31,31,31,31,31,31,
            31,31,31,31,31,31,31,31,31,31,
            31,31,31,31,31,31,31,16,16,16,
            16,16,16,16,16,16,16,16,16,16,
            16,16,16,16,16,16,16,16,16,16,
            16,16,16,16,16,16,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,17,17,17,17,17,
            17,17,17,17,17,7,7,7,7,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,7,7,7,7,7,
            7,7,7,7,7,14,14,14,14,30,
            30,30,30,30,30,30,30,18,18,18,
            18,18,8,8,8,8,8,5,19,32,
            32,10,10,12,12,33,23,23,23,23,
            23,23,23,23,23,24,20,20,20,20,
            20,20,8,8,8,805,506,505,505,505,
            2,449,1278,54,507,1289,488,799,907,434,
            434,434,434,1343,162,347,6,7,8,9,
            471,1077,401,345,378,434,434,434,326,203,
            508,508,508,508,508,985,508,508,1340,423,
            434,448,203,508,508,508,508,508,406,508,
            508,43,423,411,469,407,439,439,439,439,
            439,1225,439,439,1338,439,1,47,47,47,
            47,1245,342,1256,483,47,1339,342,1344,483,
            47,1345,47,507,312,312,312,312,312,1078,
            312,312,1155,454,707,310,310,310,310,607,
            312,312,312,312,312,1079,312,312,1290,1231,
            54,310,310,310,102,45,45,45,45,1267,
            485,1227,44,45,411,485,1299,54,45,1291,
            45,303,52,52,52,52,1005,466,493,493,
            493,1150,466,1316,54,1327,54,493,1083,165,
            164,164,164,1157,165,164,164,164,1229,1346,
            1348,52,52,1351,1294,1296,1297,1310,1313,511,
            511
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,17,18,19,
            20,21,22,23,24,25,26,27,28,29,
            30,31,32,33,34,35,36,37,38,39,
            40,41,42,43,44,45,46,47,48,49,
            50,51,52,53,54,55,56,57,58,59,
            60,61,62,63,64,65,66,67,68,69,
            70,71,72,73,74,75,76,77,78,79,
            80,81,82,83,84,85,86,87,88,89,
            90,91,92,93,94,95,96,97,98,99,
            100,0,1,2,3,4,5,6,7,8,
            9,10,11,12,13,14,15,16,17,18,
            19,20,21,22,23,24,25,26,27,28,
            29,30,31,32,33,34,35,36,37,38,
            39,40,41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,57,58,
            59,60,61,62,63,64,65,66,67,68,
            69,70,71,72,73,74,75,76,77,78,
            79,80,81,82,83,84,85,86,87,88,
            89,90,91,92,93,94,95,96,97,98,
            99,100,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            88,89,90,91,92,93,94,95,96,97,
            98,99,0,1,2,3,4,5,6,7,
            8,9,10,11,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,66,67,
            68,69,70,71,72,73,74,75,76,77,
            78,79,80,81,82,83,84,85,86,87,
            88,89,90,91,92,93,94,95,96,97,
            0,0,100,0,0,103,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,92,93,
            94,95,96,97,98,99,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,78,79,80,81,82,83,
            84,85,86,87,88,89,90,91,0,93,
            94,95,96,97,0,1,2,3,4,5,
            6,7,8,9,10,11,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            66,67,68,69,70,71,72,0,74,75,
            76,77,78,79,80,81,82,83,84,85,
            86,87,88,89,90,91,92,0,0,0,
            102,0,98,99,100,101,0,1,2,3,
            4,5,6,7,8,9,10,11,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,66,67,68,69,70,71,72,73,
            74,75,76,77,0,79,80,81,82,83,
            84,85,86,87,88,89,90,91,14,93,
            94,95,96,97,0,1,2,3,4,5,
            6,7,8,9,10,0,12,13,14,15,
            16,17,18,19,20,21,22,23,24,25,
            26,27,28,29,30,31,32,33,34,35,
            36,37,38,39,40,41,42,43,44,45,
            46,47,48,49,50,51,52,53,54,55,
            56,57,58,59,60,61,62,63,64,65,
            0,67,68,69,70,71,0,0,0,0,
            76,77,0,1,2,3,4,5,6,7,
            8,9,10,15,12,13,14,15,16,17,
            18,19,20,21,22,23,24,25,26,27,
            28,29,30,31,32,33,34,35,36,37,
            38,39,40,41,42,43,44,45,46,47,
            48,49,50,51,52,53,54,55,56,57,
            58,59,60,61,62,63,64,65,71,0,
            74,0,0,0,0,73,0,1,2,3,
            4,5,6,7,8,9,10,0,12,13,
            14,15,16,17,18,19,20,21,22,23,
            24,25,26,27,28,29,30,31,32,33,
            34,35,36,37,38,39,40,41,42,43,
            44,45,46,47,48,49,50,51,52,53,
            54,55,56,57,58,59,60,61,62,63,
            64,65,68,69,0,0,0,78,0,73,
            0,1,2,3,4,5,6,7,8,9,
            10,0,12,13,0,1,2,3,4,5,
            6,7,8,9,10,0,1,2,3,4,
            5,6,7,8,9,10,0,1,2,3,
            4,5,6,7,8,9,10,0,1,2,
            3,4,5,6,7,8,9,10,0,0,
            0,67,66,0,66,0,0,67,0,1,
            2,3,4,5,6,7,8,9,10,0,
            0,0,0,69,70,0,1,2,3,4,
            5,6,7,8,9,10,0,1,2,3,
            4,5,6,7,8,9,10,0,0,0,
            0,0,0,0,0,0,0,0,11,11,
            0,12,13,11,11,11,0,12,11,66,
            72,11,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            92,0,0,0,0,0,98,99,100,0,
            101,101,0,0,0,0,0,102,102,0,
            0,0,0,0,0,0,68,0,0,0,
            0,102,75,101,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            511,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,454,558,558,558,558,558,557,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,558,558,558,558,558,558,558,558,558,
            558,511,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,561,556,556,556,556,556,522,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,556,556,556,556,556,556,556,556,
            556,556,313,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,461,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,508,508,508,508,508,508,508,508,
            508,508,10,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            563,563,563,563,563,563,563,563,563,563,
            48,511,563,511,511,563,511,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,498,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,439,439,439,439,
            439,439,439,439,439,439,314,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,461,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,511,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,504,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,823,823,823,823,
            823,823,823,823,823,823,511,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,821,821,821,821,821,821,
            821,821,821,821,826,821,821,821,821,821,
            821,821,821,821,821,821,821,821,511,821,
            821,821,821,481,511,506,506,506,506,506,
            506,506,506,506,506,532,505,505,505,505,
            505,505,505,505,505,505,505,505,505,505,
            505,505,505,505,505,505,505,505,505,505,
            505,505,505,505,505,505,505,505,505,505,
            505,505,505,505,505,505,505,505,505,505,
            505,505,505,505,505,505,505,505,505,505,
            395,401,418,432,421,526,507,511,361,404,
            416,429,348,541,353,375,369,545,538,539,
            536,537,551,542,528,529,507,511,511,511,
            510,511,507,507,507,382,511,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,434,434,434,434,434,434,
            434,434,434,434,329,434,434,434,434,434,
            434,434,434,434,434,434,434,434,445,434,
            434,434,434,481,511,466,466,466,466,466,
            466,466,466,466,466,511,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            493,493,493,493,493,493,493,493,493,493,
            511,836,500,829,828,830,38,16,511,511,
            503,831,1,676,676,676,676,676,676,676,
            676,676,676,499,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,362,511,
            550,511,511,511,14,677,316,676,676,676,
            676,676,676,676,676,676,676,511,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,675,675,675,675,675,675,675,675,
            675,675,540,562,35,511,511,819,307,677,
            6,565,565,565,565,565,565,565,565,565,
            565,511,411,411,511,342,342,342,342,342,
            342,342,342,342,342,511,483,483,483,483,
            483,483,483,483,483,483,511,485,485,485,
            485,485,485,485,485,485,485,58,565,565,
            565,565,565,565,565,565,565,565,12,511,
            511,547,818,3,818,163,53,476,57,565,
            565,565,565,565,565,565,565,565,565,161,
            511,511,311,459,413,60,565,565,565,565,
            565,565,565,565,565,565,59,565,565,565,
            565,565,565,565,565,565,565,32,20,7,
            511,511,330,19,13,511,511,324,838,534,
            321,411,411,842,533,839,511,548,834,818,
            673,833,511,511,511,511,511,511,511,511,
            511,511,511,511,511,511,511,511,511,511,
            673,511,511,511,511,511,673,673,673,511,
            516,515,511,511,511,511,511,1,6,511,
            511,511,511,511,511,511,535,511,511,511,
            511,12,544,314
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 50,
           NT_OFFSET         = 103,
           LA_STATE_OFFSET   = 845,
           MAX_LA            = 1,
           NUM_RULES         = 334,
           NUM_NONTERMINALS  = 40,
           NUM_SYMBOLS       = 143,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 335,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 102,
           EOLT_SYMBOL       = 104,
           ACCEPT_ACTION     = 510,
           ERROR_ACTION      = 511;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
