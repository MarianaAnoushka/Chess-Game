package src.pieces;

import src.Piece;
import src.Position;
import src.PieceColor;

public class Bishop extends Piece {

    public Bishop(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if(newPosition.equals(position)) {
            return false; //cannot move to same place
        }
        int rowDiff = Math.abs(newPosition.getRow() - position.getRow());
        int colDiff = Math.abs(newPosition.getColumn() - position.getColumn());

        //Check if move is diagonal
        if(rowDiff != colDiff) {
            return false;
        }

        int rowStep = newPosition.getRow() > position.getRow() ? 1 : -1;
        int colStep = newPosition.getColumn() > position.getColumn() ? 1 : -1;
        int steps = rowDiff - 1; // Number of squares to check for obstruction

        for(int i = 1; i <= steps; i++) {
            if(board[position.getRow() + i * rowStep][position.getColumn() + i * colStep] != null) {
                return false;
            }
        }
        
        //check if we will be capturing a piece
        Piece destinationPiece = board[newPosition.getRow()][newPosition.getColumn()];
        if(destinationPiece == null) {
            return true; //there is no piece to capture and the move is valid
        }
        else return destinationPiece.getColor() != this.color;
    }
}
