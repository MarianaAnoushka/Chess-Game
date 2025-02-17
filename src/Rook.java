package src;

public class Rook extends Piece {

    public Rook(PieceColor color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(Position newPosition, Piece[][] board) {
        if(newPosition.equals(position)) {
            return false; //cannot move to same position
        }
        //moving horizontally
        if(position.getRow() == newPosition.getRow()) {
            int colStart = Math.min(position.getColumn(), newPosition.getColumn());
            int colEnd = Math.max(position.getColumn(), newPosition.getColumn());
            for(int col = colStart; col <= colEnd; col++) {
                if(board[newPosition.getRow()][col] != null) {
                    return false; //there is a piece in the way
                }
            }
        }
        //moving vertically
        else if(position.getColumn() == newPosition.getColumn()) {
            int rowStart = Math.min(position.getRow(), newPosition.getRow());
            int rowEnd = Math.max(position.getRow(), newPosition.getRow());
            for(int row = rowStart; row <= rowEnd; row++) {
                if(board[row][newPosition.getColumn()] != null) {
                    return false;
                }
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
